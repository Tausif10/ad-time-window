package services

import java.util.Date

import models.{Log, MinTimeAdWindow}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import utils.DateUtils

class AdTimeWindowCalculatorSpec extends Specification {

  "Time window calculator" should {

    "return min time window for ads" in new Fixture {
      val expectedTimeWindow = MinTimeAdWindow(date1, date3, 15L)
      timeWindowCalculator.getMinTimeAdWindow(inputAdIds, logs) must beSome(expectedTimeWindow)
    }

    "return none when there is no log for input ad ids" in new Fixture {
      timeWindowCalculator.getMinTimeAdWindow(List("A1", "A5"), logs) must beNone
    }
  }

  trait Fixture extends Scope {
    val inputAdIds = List("A1", "A2", "A3")
    val date1: Date = DateUtils.convertToDate("10:45am Jan 01")
    val date2: Date = DateUtils.convertToDate("10:50am Jan 01")
    val date3: Date = DateUtils.convertToDate("11:00am Jan 01")
    val logs = List(Log("I1", "A1", "U2", date1),
      Log("I2", "A2", "U2", date2),
      Log("I3", "A3", "U2", date3))
    val timeWindowCalculator = new AdTimeWindowCalculator()
  }
}
