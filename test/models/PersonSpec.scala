import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

class PersonSpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {
  import models._

  import scala.concurrent.ExecutionContext.Implicits.global

  def personService: PersonRepository = app.injector.instanceOf(classOf[PersonRepository])

  "Person model" should {

    "be retrieved by id" in {
      whenReady(personService.findById(1)) { maybePerson =>
        val macintosh = maybePerson.get

        macintosh.firstName.get must equal("John")
        macintosh.lastName.get must equal("Snow")
        macintosh.phone.get must equal("123456789")
        macintosh.email.get must equal("johnsnow@edu.p.lodz.pl")
      }
    }
    
    "be listed along its firstName" in {
        whenReady(personService.list()) { people =>

          people.total must equal(3)
          people.items must have length(3)
        }
    }
    
    "be updated if needed" in {

      val result = personService.findById(1).flatMap { computer =>
        personService.update(1, Person(firstName=Some("Jan"),
          lastName=Some("Nowak"),
          phone=Some("111222333"),
          email=Some("test@edu.p.lodz.pl"),
          )).flatMap { _ =>
          personService.findById(1)
        }
      }

      whenReady(result) { person =>
        val macintosh = person.get

        macintosh.firstName.get must equal("Jan")
        macintosh.lastName.get must equal("Nowak")
        macintosh.phone.get must equal("111222333")
        macintosh.email.get must equal("test@edu.p.lodz.pl")
      }
    }
  }
}