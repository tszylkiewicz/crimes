package models

import java.util.Date
import anorm._


object Rank extends Enumeration { 
  val Constable, Sergeant, Aspirant, Commissioner, Inspector, Detective = Value
} 

case class Person(id: Option[Long] = None,                    
                    firstName: Option[String],
                    lastName: Option[String],
                    phone: Option[String],
                    email: Option[String],
                    rank: Option[String])

object Person {
  implicit def toParameters: ToParameterList[Person] =
    Macro.toParameters[Person]
}