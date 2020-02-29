package services

import readers.LogReader
import writers.OutputWriter

import scala.util.{Failure, Success}


class LogProcessor(logReader: LogReader,
                   adTimeWindowCalculator: AdTimeWindowCalculator,
                   outputWriter: OutputWriter) {

  val errorMsg = "There is no log for some of the ad ids..."
  val fileError = "Something went wrong with file reading..."

  def process(inputAdIds: String): String = {
    val adIds = inputAdIds.split(",").toList
    logReader.read() match {
      case Success(logs) =>
        adTimeWindowCalculator.getMinTimeAdWindow(adIds, logs).
          map(outputWriter.write(_)).getOrElse(errorMsg)
      case Failure(exception) => s"$fileError Error:${exception.printStackTrace}"
    }
  }


}

