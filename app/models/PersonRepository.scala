package models

import java.util.Date
import javax.inject.Inject

import anorm.SqlParser.{ get, scalar }
import anorm._
import play.api.db.DBApi

import scala.concurrent.Future
import scala.util.{ Failure, Success }

import models.Person


@javax.inject.Singleton
class PersonRepository @Inject()(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  
  private[models] val simple = {
    get[Option[Long]]("person.id") ~      
      get[Option[String]]("person.firstName") ~
      get[Option[String]]("person.lastName") ~
      get[Option[String]]("person.phone") ~
      get[Option[String]]("person.email") map {
      case id ~ firstName ~ lastName ~ phone ~ email =>
        Person(id, firstName, lastName, phone, email)
    }
  }

private val toReturn = simple map {
    case person => person
  }
  
  def findById(id: Long): Future[Option[Person]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from person where id = $id".as(simple.singleOpt)
    }
  }(ec)

  
  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[(Person)]] = Future {

    val offset = pageSize * page

    db.withConnection { implicit connection =>

      val people = SQL"""
        select * from person
        where person.firstName like ${filter}
        order by ${orderBy} nulls last
        limit ${pageSize} offset ${offset}
      """.as(toReturn.*)

      val totalRows = SQL"""
        select count(*) from person
        where person.firstName like ${filter}
      """.as(scalar[Long].single)

      Page(people, page, offset, totalRows)
    }
  }(ec)

  def update(id: Long, person: Person) = Future {
    db.withConnection { implicit connection =>
      SQL("""
        update person set firstName = {firstName}, lastName = {lastName}, 
          phone = {phone}, email = {email}
        where id = {id}
      """).bind(person.copy(id = Some(id)/* ensure */)).executeUpdate()
      // case class binding using ToParameterList,
      // note using SQL(..) but not SQL.. interpolation
    }
  }(ec)

  
  def insert(person: Person): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL("""
        insert into person values (
          (select next value for crime_seq),
          {firstName}, {lastName}, {phone}, {email}
        )
      """).bind(person).executeInsert()
    }
  }(ec)

  
  def delete(id: Long) = Future {
    db.withConnection { implicit connection =>
      SQL"update crime set person_id = null where person_id = ${id}".executeUpdate()
      SQL"delete from person where id = ${id}".executeUpdate()
    }
  }(ec)

  def options: Future[Seq[(String,String)]] = Future(db.withConnection { implicit connection =>
    SQL"select * from person order by firstName".
      fold(Seq.empty[(String, String)], ColumnAliaser.empty) { (acc, row) => // Anorm streaming
        row.as(simple) match {
          case Failure(parseErr) => {
            println(s"Fails to parse $row: $parseErr")
            acc
          }

          case Success(Person(Some(id), firstName, lastName, phone, email)) =>
            (id.toString -> firstName.get.concat(" ").concat(lastName.get)) +: acc

          case Success(Person(None, _, _, _, _)) => acc
        }
      }
  }).flatMap {
    case Left(err :: _) => Future.failed(err)
    case Left(_) => Future(Seq.empty)
    case Right(acc) => Future.successful(acc.reverse)
  }

}