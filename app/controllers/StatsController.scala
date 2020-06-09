package controllers

import javax.inject.Inject
import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import views._
import play.api.data.format.Formats._

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

  def generate(page: Int, orderBy: Int, filter: String) = Action.async { implicit request =>
    getList(page = page, orderBy = orderBy, filter = ("%" + filter + "%")).map { page =>
      Ok(html.statsList(page, orderBy, filter))
    }
  }

  def getList(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Future[Page[(Record)]] = Future {

    val offset = pageSize * page
    var records:Array[Record]=new Array[Record](1)
    records(0) = Record("a", 1, "10%")
      // val records = [Record("a", 1, "10%"), Record("a", 1, "10%")]

      val totalRows = 1

      Page(records, page, offset, totalRows)

  }(ec)
}