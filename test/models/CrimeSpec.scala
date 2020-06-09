import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

class ModelSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {
  import models._

  import scala.concurrent.ExecutionContext.Implicits.global

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = {
    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  }
  
  // --

  def crimeService: CrimeRepository = app.injector.instanceOf(classOf[CrimeRepository])

  "Crime model" should {

    "be retrieved by id" in {
      whenReady(crimeService.findById(1)) { maybeCrime =>
        val macintosh = maybeCrime.get

        macintosh.description must equal("Battery")
        macintosh.categoryId must equal(Some(1))
        macintosh.date.value must matchPattern {
          case date:java.util.Date if dateIs(date, "1993-10-21") =>
        }
        macintosh.resolution must equal(Some("Arrest"))
        macintosh.street must equal(Some("Politechniki"))
        macintosh.city must equal(Some("Lodz"))
        macintosh.district must equal(Some("lodzkie"))
        macintosh.latitude must equal(Some(81.172633))
        macintosh.longitude must equal(Some(85.581219))
        macintosh.personId must equal(Some(1))
      }
    }
    
    "be listed along its categories" in {
        whenReady(crimeService.list()) { crimes =>

          crimes.total must equal(3)
          crimes.items must have length(3)
        }
    }
    
    "be updated if needed" in {

      val result = crimeService.findById(1).flatMap { computer =>
        crimeService.update(1, Crime(description="Description",
          date=None,
          resolution=Some("Resolution"),
          categoryId=Some(2),
          street=Some("Street"),
          city=Some("City"),
          district=Some("District"),
          latitude=Some(11.111111),
          longitude=Some(22.222222),
          personId=Some(2))).flatMap { _ =>
          crimeService.findById(1)
        }
      }

      whenReady(result) { crime =>
        val macintosh = crime.get

        macintosh.description must equal("Description")
        macintosh.categoryId must equal(Some(2))
        macintosh.date mustBe None
        macintosh.resolution must equal(Some("Resolution"))
        macintosh.street must equal(Some("Street"))
        macintosh.city must equal(Some("City"))
        macintosh.district must equal(Some("District"))
        macintosh.latitude must equal(Some(11.111111))
        macintosh.longitude must equal(Some(22.222222))
        macintosh.personId must equal(Some(2))
      }
    }
  }
}