import controllers.PersonController
import org.scalatest.concurrent.ScalaFutures
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.CSRFTokenHelper._

class PersonControllerSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {

  def personController = app.injector.instanceOf(classOf[PersonController])

  "PersonController" should {

    "list people on the the page" in {
      val result = personController.list(0, 2, "")(FakeRequest())

      status(result) must equal(OK)
      contentAsString(result) must include("6 people found")
    }

    "filter people by firstName" in {
      val result = personController.list(0, 2, "John")(FakeRequest())

      status(result) must equal(OK)
      contentAsString(result) must include("One person found")
    }


    "create new person" in {
      val result = personController.save(
        FakeRequest().withFormUrlEncodedBody("firstName" -> "Clarke", "lastName" -> "Griffin", "phone" -> "Clarke", "email" -> "clarkegriffin.edu.p.lodz.pl").withCSRFToken
      )

      status(result) must equal(SEE_OTHER)
      redirectLocation(result) mustBe Some("/people")
      flash(result).get("success") mustBe Some("Person Clarke has been created")

      val list = personController.list(0, 2, "Clarke")(FakeRequest())

      status(list) must equal(OK)
      contentAsString(list) must include("One person found")
    }
  }
}