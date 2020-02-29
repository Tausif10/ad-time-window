package writers

import models.MinTimeAdWindow

class TimeWindowOutputWriter extends OutputWriter {

  override def write(result: MinTimeAdWindow): String = {
    s"""
       |Start Time: ${result.startTime}
       |End       : ${result.endTime}
       |Duration  : ${result.duration} mins
       |""".stripMargin
  }
}
