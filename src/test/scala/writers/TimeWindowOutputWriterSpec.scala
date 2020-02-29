package writers

import models.MinTimeAdWindow
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import utils.DateUtils

class TimeWindowOutputWriterSpec extends Specification {

  "Time window output writer" should {

    "return time window formatted output if input is in correct format" in new Fixture {
      val expectedResult = """
                             |Start Time: Thu Jan 01 10:00:00 IST 1970
                             |End       : Thu Jan 01 11:00:00 IST 1970
                             |Duration  : 60 mins
                             |""".stripMargin
      timeWindowOutputWriter.write(result) mustEqual expectedResult
    }
  }
}

trait Fixture extends Scope {
  val timeWindowOutputWriter = new TimeWindowOutputWriter
  val date1 = DateUtils.convertToDate("10:00am Jan 01")
  val date2 = DateUtils.convertToDate("11:00am Jan 01")
  val result = models.MinTimeAdWindow(date1, date2, 60L)
}
