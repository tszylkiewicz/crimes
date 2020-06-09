import controllers.CrimeController
import org.scalatest.concurrent.ScalaFutures
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.CSRFTokenHelper._

class CrimeControllerSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {

  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }

  def crimeController = app.injector.instanceOf(classOf[CrimeController])

  "CrimeController" should {

    "redirect to the crime list on /" in {
      val result = crimeController.index(FakeRequest())

      status(result) must equal(SEE_OTHER)
      redirectLocation(result) mustBe Some("/crimes")
    }

    "list crimes on the the first page" in {
      val result = crimeController.list(0, 2, "")(FakeRequest())

      status(result) must equal(OK)
      contentAsString(result) must include("3 crimes found")
    }

    "filter crimes by description" in {
      val result = crimeController.list(0, 2, "Battery")(FakeRequest())

      status(result) must equal(OK)
      contentAsString(result) must include("3 crimes found")
    }

    //running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {

    "create new crime" in {
      val badResult = crimeController.save(FakeRequest().withCSRFToken)

      status(badResult) must equal(BAD_REQUEST)

      val badDateFormat = crimeController.save(
        FakeRequest().withFormUrlEncodedBody("description" -> "Description", "date" -> "2019-01-15", "resolution" -> "None", "category" -> "2",
          "street" -> "Moniuszki", "city" -> "Warsaw", "district" -> "Praga", "latitude" -> "11.111111", "longitude" -> "22.222222", "person" -> "2").withCSRFToken
      )

      status(badDateFormat) must equal(BAD_REQUEST)
      contentAsString(badDateFormat) must include("""<option value="2" selected="selected">Burglary</option>""")
      contentAsString(badDateFormat) must include("""<input type="text" id="date" name="date" value="2019-01-15" """)
      contentAsString(badDateFormat) must include("""<input type="text" id="description" name="description" value="Description" """)


      val result = crimeController.save(
        FakeRequest().withFormUrlEncodedBody("description" -> "Description", "date" -> "2019-01-15", "resolution" -> "None", "category" -> "2",
          "street" -> "Moniuszki", "city" -> "Warsaw", "district" -> "Praga", "latitude" -> "11.111111", "longitude" -> "22.222222", "person" -> "2").withCSRFToken
      )

      status(result) must equal(SEE_OTHER)
      redirectLocation(result) mustBe Some("/crimes")
      flash(result).get("success") mustBe Some("Crime Description has been created")

      val list = crimeController.list(0, 2, "Description")(FakeRequest())

      status(list) must equal(OK)
      contentAsString(list) must include("One crime found")
    }
  }
}