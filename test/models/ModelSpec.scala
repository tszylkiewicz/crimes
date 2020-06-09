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

        macintosh.description must equal(Some("Battery"))
        macintosh.date.value must matchPattern {
          case date:java.util.Date if dateIs(date, "1993-10-21") =>
        }
        macintosh.resolution must equal(Some("Arrest"))
        macintosh.street must equal(Some("Politechniki"))
        macintosh.city must equal(Some("Lodz"))
        macintosh.district must equal(Some("lodzkie"))
        macintosh.latitude must equal(Some(81.172633))
        macintosh.longitude must equal(Some(85.581219))
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
        crimeService.update(1, Crime(description=Some("The Macintosh"),
          date=None,
          resolution=Some("Arrest"),
          categoryId=Some(1),
          street=Some("Politechniki"),
          city=Some("Lodz"),
          district=Some("lodzkie"),
          latitude=Some(81.172633),
          longitude=Some(85.581219))).flatMap { _ =>
          crimeService.findById(1)
        }
      }

      whenReady(result) { crime =>
        val macintosh = crime.get

        macintosh.description must equal(Some("The Macintosh"))
        macintosh.date mustBe None
        macintosh.resolution must equal(Some("Arrest"))
        macintosh.street must equal(Some("Politechniki"))
        macintosh.city must equal(Some("Lodz"))
        macintosh.district must equal(Some("lodzkie"))
        macintosh.latitude must equal(Some(81.172633))
        macintosh.longitude must equal(Some(85.581219))
      }
    }
  }
}