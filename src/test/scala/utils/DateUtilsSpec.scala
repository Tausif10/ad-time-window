package utils

import org.specs2.mutable.Specification

class DateUtilsSpec extends Specification {

  "Date util" should {

    "convert string into date when string is in specified format" in {
      val date = DateUtils.convertToDate("10:00am Jan 01")
      date.toString mustEqual "Thu Jan 01 10:00:00 IST 1970"
    }

    "throw exception when string is not in specified format" in {
      DateUtils.convertToDate("10:00am") must throwAn()
    }
  }
}
