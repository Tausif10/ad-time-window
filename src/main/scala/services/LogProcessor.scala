package services

import readers.LogReader
import writers.OutputWriter

class LogProcessor(logReader: LogReader,
                   adTimeWindowCalculator: AdTimeWindowCalculator,
                   outputWriter: OutputWriter) {

  val errorMsg = "There is no log for some of the ad ids..."

  def process(inputAdIds: String): String = {
    val adIds = inputAdIds.split(",").toList
    val maybeMinTimeWindow = adTimeWindowCalculator.getMinTimeAdWindow(adIds, logReader.read())

    if (maybeMinTimeWindow.isDefined)
      outputWriter.write(maybeMinTimeWindow.get)
    else
      errorMsg
  }
}

