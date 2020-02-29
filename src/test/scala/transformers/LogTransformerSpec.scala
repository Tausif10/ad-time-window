package transformers

import java.util.Date

import models.Log
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import utils.DateUtils

class LogTransformerSpec extends Specification {

  "Log transformer" should {

    "build log from string" in new Fixture {
      val expectedLogs = List(Log("I1","A1","U1",DateUtils.convertToDate("10:00am Jan 01")),
      Log("I2","A3","U1",DateUtils.convertToDate("10:45am Jan 01")))
      val logs = logTransformer.transform(logsFromFile)
      logs.size mustEqual 2
      logs mustEqual expectedLogs
    }

    "filter log which does not contain all logs details" in new Fixture {
      val logs = logTransformer.transform(logsFromFile)
      logs.size must beLessThan(logsFromFile.size)
    }
  }
}

trait Fixture extends Scope {
  val logTransformer = new LogTransformer()
  val logsFromFile = scala.io.Source.fromFile("src/test/scala/resources/TestLog").getLines().toList
}