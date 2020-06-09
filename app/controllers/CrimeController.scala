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
                               personService: PersonRepository,
                               cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  val Home = Redirect(routes.CrimeController.list(0, 2, ""))

  val crimeForm = Form(
    mapping(
      "id" -> ignored(None: Option[Long]),      
      "description" -> nonEmptyText,
      "date" -> optional(date("yyyy-MM-dd")),
      "resolution" -> optional(text),
      "category" -> optional(longNumber),
      "street" -> optional(text),
      "city" -> optional(text),
      "district" -> optional(text),
      "latitude" -> optional(of(doubleFormat)),
      "longitude" -> optional(of(doubleFormat)),
      "person" -> optional(longNumber)
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
      personService.options.flatMap { options2 =>
        categoryService.options.map { options =>
          Ok(html.editForm(id, crimeForm.fill(crime), options, options2))
        }}
      case other =>
        Future.successful(NotFound)
    }
  }

 
  def update(id: Long) = Action.async { implicit request =>
    crimeForm.bindFromRequest.fold(
      formWithErrors => {
        logger.warn(s"form error: $formWithErrors")
        personService.options.flatMap { options2 =>
        categoryService.options.map { options =>
          BadRequest(html.editForm(id, formWithErrors, options, options2))
        }}
      },
      crime => {
        crimeService.update(id, crime).map { _ =>
          Home.flashing("success" -> "Crime %s has been updated".format(crime.description))
        }
      }
    )
  }

  
  def create = Action.async { implicit request =>
  personService.options.flatMap { options2 =>
    categoryService.options.map { options =>
      Ok(html.createForm(crimeForm, options, options2))
    }}
  }

  
  def save = Action.async { implicit request =>
    crimeForm.bindFromRequest.fold(
      formWithErrors => personService.options.flatMap { options2 => categoryService.options.map { options =>
        BadRequest(html.createForm(formWithErrors, options, options2))
      }},
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