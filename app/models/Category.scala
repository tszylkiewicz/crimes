package models

import anorm._


case class Category(id: Option[Long] = None, 
                    name: String)

object Category {
  implicit def toParameters: ToParameterList[Category] =
    Macro.toParameters[Category]
}