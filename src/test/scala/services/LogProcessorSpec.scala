package services

import java.util.Date

import models.{Log, MinTimeAdWindow}
import org.specs2.matcher.Scope
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import readers.FileLogReader
import utils.DateUtils
import writers.TimeWindowOutputWriter

import scala.util.{Failure, Success}

class LogProcessorSpec extends Specification with Mockito {

  "Log processor" should {

    "process and write result in specified format" in new Fixture {
      mockFileLogReader.read() returns Success(logs)
      mockTimeWindowCalculator.getMinTimeAdWindow(List("A1", "A2"), logs) returns
        Some(MinTimeAdWindow(date1, date2, 5L))
      mockTimeWindowOutputWriter.write(MinTimeAdWindow(date1, date2, 5L)) returns "start: 10:00 end:12:00"
      logProcessor.process(inputAdIds) mustEqual ("start: 10:00 end:12:00")
      there was one(mockFileLogReader).read()
      there was one(mockTimeWindowOutputWriter).write(MinTimeAdWindow(date1, date2, 5L))
      there was one(mockTimeWindowCalculator).getMinTimeAdWindow(List("A1", "A2"), logs)
    }

    "return error msg when there is no log for given input ad ids" in new Fixture {
      mockFileLogReader.read() returns Success(logs)
      mockTimeWindowCalculator.getMinTimeAdWindow(List("A1", "A2"), logs) returns None
      logProcessor.process(inputAdIds) mustEqual "There is no log for some of the ad ids..."
      there was one(mockTimeWindowCalculator).getMinTimeAdWindow(List("A1", "A2"), logs)
      there was no(mockTimeWindowOutputWriter).write(any())
    }

    "return file error msg when there is issue while reading the file" in new Fixture {
      mockFileLogReader.read() returns Failure(new Exception("file not found"))
      logProcessor.process(inputAdIds) mustEqual "Something went wrong with file reading... Error:()"
      there was no(mockTimeWindowCalculator).getMinTimeAdWindow(List("A1", "A2"), logs)
      there was no(mockTimeWindowOutputWriter).write(any())
    }
  }

  trait Fixture extends Scope {
    val inputAdIds = "A1,A2"
    val date1: Date = DateUtils.convertToDate("10:45am Jan 01")
    val date2: Date = DateUtils.convertToDate("10:50am Jan 01")
    val logs = List(Log("I1", "A1", "U2", date1),
      Log("I1", "A2", "U2", date2))
    val mockFileLogReader = mock[FileLogReader]
    val mockTimeWindowCalculator = mock[AdTimeWindowCalculator]
    val mockTimeWindowOutputWriter = mock[TimeWindowOutputWriter]
    val logProcessor = new LogProcessor(mockFileLogReader, mockTimeWindowCalculator, mockTimeWindowOutputWriter)
  }

}
