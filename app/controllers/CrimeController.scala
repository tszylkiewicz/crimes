package controllers

import javax.inject.Inject

import models.Crime
import play.api.data._
import play.api.i18n._
import play.api.mvc._

import scala.collection._

/**
 * The classic WidgetController using MessagesAbstractController.
 *
 * Instead of MessagesAbstractController, you can use the I18nSupport trait,
 * which provides implicits that create a Messages instance from a request
 * using implicit conversion.
 *
 * See https://www.playframework.com/documentation/2.8.x/ScalaForms#passing-messagesprovider-to-form-helpers
 * for details.
 */
class CrimeController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import CrimeForm._

  private val crimes = mutable.ArrayBuffer(
    Crime("burglary", "BURGLARY,STORE UNDER CONSTRUCTION, FORCIBLE ENTRY", "Lodz", "01-01-2020"),
    Crime("theft", "LOST PROPERTY", "Warsaw", "05-04-2020"),
    Crime("assault", "BATTERY", "Radom", "13-04-2020")
  )

  // The URL to the widget.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless i.e. all
  // of the "WidgetController" references are inside the .scala file.
  private val postUrl = routes.CrimeController.createCrime()

  def index = Action {
    Ok(views.html.index())
  }

  def listCrimes = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.listCrimes(crimes.toSeq, form, postUrl))
  }

  // This will be the action that handles our form post
  def createCrime = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.listCrimes(crimes.toSeq, formWithErrors, postUrl))
    }

    val successFunction = { data: Data =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val crime = Crime(category = data.category, description = data.description, city = data.city, date = data.date)
      crimes += crime
      Redirect(routes.CrimeController.listCrimes()).flashing("info" -> "Crime added!")
    }

    val formValidationResult = form.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }
}