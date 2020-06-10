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
      crimeService.listAll().map { page =>
      val crimesData = Map("District" -> page.groupBy(_.district).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat*100f)).toList,
      "City" -> page.groupBy(_.city).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat*100f)).toList,
      "Street" -> page.groupBy(_.street).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat*100f)).toList,
      "Category" -> page.groupBy(_.category).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat*100f)).toList,
      "Resolution" -> page.groupBy(_.resolution).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat*100f)).toList,
      "Person" -> page.groupBy(_.person).map(t=>(t._1, t._2.length, t._2.length.toFloat/page.length.toFloat*100f)).toList)  
      Ok(html.statsList(crimesData, orderBy, filter))
    }
  }

}