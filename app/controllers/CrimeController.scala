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
class CrimeController @Inject()(crimeService: CrimeRepository,
                               categoryService: CategoryRepository,
                               cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  val Home = Redirect(routes.CrimeController.list(0, 2, ""))

  val crimeForm = Form(
    mapping(
      "id" -> ignored(None: Option[Long]),      
      "description" -> optional(text),
      "date" -> optional(date("yyyy-MM-dd")),
      "resolution" -> optional(text),
      "category" -> optional(longNumber),
      "street" -> optional(text),
      "city" -> optional(text),
      "district" -> optional(text),
      "latitude" -> optional(of(doubleFormat)),
      "longitude" -> optional(of(doubleFormat))
    )(Crime.apply)(Crime.unapply)
  )


  def index = Action {
    Home
  }


  def list(page: Int, orderBy: Int, filter: String) = Action.async { implicit request =>
    crimeService.list(page = page, orderBy = orderBy, filter = ("%" + filter + "%")).map { page =>
      Ok(html.list(page, orderBy, filter))
    }
  }

  
  def edit(id: Long) = Action.async { implicit request =>
    crimeService.findById(id).flatMap {
      case Some(crime) =>
        categoryService.options.map { options =>
          Ok(html.editForm(id, crimeForm.fill(crime), options))
        }
      case other =>
        Future.successful(NotFound)
    }
  }

 
  def update(id: Long) = Action.async { implicit request =>
    crimeForm.bindFromRequest.fold(
      formWithErrors => {
        logger.warn(s"form error: $formWithErrors")
        categoryService.options.map { options =>
          BadRequest(html.editForm(id, formWithErrors, options))
        }
      },
      crime => {
        crimeService.update(id, crime).map { _ =>
          Home.flashing("success" -> "Crime %s has been updated".format(crime.description))
        }
      }
    )
  }

  
  def create = Action.async { implicit request =>
    categoryService.options.map { options =>
      Ok(html.createForm(crimeForm, options))
    }
  }

  
  def save = Action.async { implicit request =>
    crimeForm.bindFromRequest.fold(
      formWithErrors => categoryService.options.map { options =>
        BadRequest(html.createForm(formWithErrors, options))
      },
      crime => {
        crimeService.insert(crime).map { _ =>
          Home.flashing("success" -> "Crime %s has been created".format(crime.description))
        }
      }
    )
  }

  
  def delete(id: Long) = Action.async {
    crimeService.delete(id).map { _ =>
      Home.flashing("success" -> "Crime has been deleted")
    }
  }

}