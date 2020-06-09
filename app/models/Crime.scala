package models

import java.util.Date
import anorm._


case class Crime(id: Option[Long] = None,                    
                    description: Option[String],
                    date: Option[Date],
                    resolution: Option[String],
                    categoryId: Option[Long],
                    street: Option[String],
                    city: Option[String],    
                    district: Option[String],
                    latitude: Option[Double],
                    longitude: Option[Double],
                    personId: Option[Long])

object Crime {
  implicit def toParameters: ToParameterList[Crime] =
    Macro.toParameters[Crime]
}