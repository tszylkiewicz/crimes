package models

import java.util.Date
import javax.inject.Inject

import anorm.SqlParser.{ get, scalar }
import anorm._
import play.api.db.DBApi

import scala.concurrent.Future

import models.Crime


case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}


@javax.inject.Singleton
class CrimeRepository @Inject()(dbapi: DBApi, categoryRepository: CategoryRepository, personRepository: PersonRepository)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  
  private val simple = {
    get[Option[Long]]("crime.id") ~      
      get[Option[String]]("crime.description") ~
      get[Option[Date]]("crime.date") ~
      get[Option[String]]("crime.resolution") ~
      get[Option[Long]]("crime.category_id") ~
      get[Option[String]]("crime.street") ~
      get[Option[String]]("crime.city") ~
      get[Option[String]]("crime.district") ~ 
      get[Option[Double]]("crime.latitude") ~
      get[Option[Double]]("crime.longitude") ~
      get[Option[Long]]("crime.person_id") map {
      case id ~ description ~ date ~ resolution ~ categoryId ~ street ~ city ~ district ~ latitude ~ longitude ~ personId =>
        Crime(id, description, date, resolution, categoryId, street, city, district, latitude, longitude, personId)
    }
  }

  
  private val withCategoryAndPerson = simple ~ (categoryRepository.simple.?) ~ (personRepository.simple.?) map {
    case crime ~ category ~ person => (crime, category, person)
  }

  
  def findById(id: Long): Future[Option[Crime]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from crime where id = $id".as(simple.singleOpt)
    }
  }(ec)

  
  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[(Crime, Option[Category], Option[Person])]] = Future {

    val offset = pageSize * page

    db.withConnection { implicit connection =>

      val crimes = SQL"""
        select * from crime
        left join category on crime.category_id = category.id
        left join person on crime.person_id = person.id
        where crime.description like ${filter}
        order by ${orderBy} nulls last
        limit ${pageSize} offset ${offset}
      """.as(withCategoryAndPerson.*)

      val totalRows = SQL"""
        select count(*) from crime
        left join category on crime.category_id = category.id
        left join person on crime.person_id = person.id
        where crime.description like ${filter}
      """.as(scalar[Long].single)

      Page(crimes, page, offset, totalRows)
    }
  }(ec)

  
  def update(id: Long, crime: Crime) = Future {
    db.withConnection { implicit connection =>
      SQL("""
        update crime set category_id = {categoryId}, description = {description}, 
          date = {date}, resolution = {resolution}, street = {street}, city = {city},
          district = {district}, latitude = {latitude}, longitude = {longitude}, person_id = {personId}
        where id = {id}
      """).bind(crime.copy(id = Some(id)/* ensure */)).executeUpdate()
      // case class binding using ToParameterList,
      // note using SQL(..) but not SQL.. interpolation
    }
  }(ec)

  
  def insert(crime: Crime): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL("""
        insert into crime values (
          (select next value for crime_seq),
          {description}, {date}, {resolution}, {categoryId}, {personId}, {personId}, {street}, {city},
          {district}, {latitude}, {longitude}
        )
      """).bind(crime).executeInsert()
    }
  }(ec)

  
  def delete(id: Long) = Future {
    db.withConnection { implicit connection =>
      SQL"delete from crime where id = ${id}".executeUpdate()
    }
  }(ec)

}