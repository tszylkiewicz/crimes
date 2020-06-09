package models

import anorm._


case class Record(parameter: String,
                    number: Long,
                    percentage: String)

object Record {
  implicit def toParameters: ToParameterList[Record] =
    Macro.toParameters[Record]
}