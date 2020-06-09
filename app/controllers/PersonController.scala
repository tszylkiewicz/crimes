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
class PersonController @Inject()(personService: PersonRepository,
                               cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  private val logger = play.api.Logger(this.getClass)

  val Home = Redirect(routes.PersonController.list(0, 2, ""))

  val personForm = Form(
    mapping(
      "id" -> ignored(None: Option[Long]),      
      "firstName" -> optional(text),
      "lastName" -> optional(text),
      "phone" -> optional(text),
      "email" -> optional(text)
    )(Person.apply)(Person.unapply)
  )


  def index = Action {
    Home
  }


  def list(page: Int, orderBy: Int, filter: String) = Action.async { implicit request =>
    personService.list(page = page, orderBy = orderBy, filter = ("%" + filter + "%")).map { page =>
      Ok(html.personList(page, orderBy, filter))
    }
  }

  def edit(id: Long) = Action.async { implicit request =>
    personService.findById(id).flatMap {
      case Some(person) =>
          Future(Ok(html.personEditForm(id, personForm.fill(person))))
      case other =>
        Future.successful(NotFound)
    }
  }

 
  def update(id: Long) = Action.async { implicit request =>
    personForm.bindFromRequest.fold(
      formWithErrors => {
        logger.warn(s"form error: $formWithErrors")
          Future(BadRequest(html.personEditForm(id, formWithErrors)))
      },
      person => {
        personService.update(id, person).map { _ =>
          Home.flashing("success" -> "Person %s has been updated".format(person.firstName.get))
        }
      }
    )
  }

  
  def create = Action.async { implicit request =>
      Future(Ok(html.personCreateForm(personForm)))
  }

  
  def save = Action.async { implicit request =>
    personForm.bindFromRequest.fold(
      formWithErrors => {
        Future(BadRequest(html.personCreateForm(formWithErrors)))
      },
      person => {
        personService.insert(person).map { _ =>
          Home.flashing("success" -> "Person %s has been created".format(person.firstName.get))
        }
      }
    )
  }

  
  def delete(id: Long) = Action.async {
    personService.delete(id).map { _ =>
      Home.flashing("success" -> "Person has been deleted")
    }
  }
}