package controllers

import javax.inject.Inject
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import views._
import play.api.data.format.Formats._
import scala.util.{Success, Failure}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Manage a database of computers
  */
class StatsController @Inject()(crimeService: CrimeRepository,
                               categoryService: CategoryRepository,
                               personService: PersonRepository,
                               cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  def generate(orderBy: Int, filter: String) = Action.async { implicit request =>
      // Ok(html.statsList(getList(), orderBy, filter))    
      crimeService.listAll().map { page =>
      Ok(html.statsList(page.groupBy(_.city).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat)).toList, orderBy, filter))
    }
  }

  // def getList(): List[(Option[String], Int, Float)] = Future {

  //   var records:Array[Record]=new Array[Record](1)
  //   records(0) = Record("a", 1, "10%")
  //   val crimesStats = crimeService.listAll()
  //   var Stats = Nil
  //   crimesStats onComplete {
  //       case Success(crimesStats) => return crimesStats.groupBy(_.city).map(t=>(t._1, t._2.length, t._2.length.toFloat/crimesStats.length.toFloat)).toList
  //       case Failure(t) => None
  //     }
  //   println(Stats)
  //     // val records = [Record("a", 1, "10%"), Record("a", 1, "10%")]
      
      

  //     Page(records, page, offset, totalRows)

  // }(ec)

}