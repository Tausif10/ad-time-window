package services

import models.{Log, MinTimeAdWindow}

import scala.annotation.tailrec

class AdTimeWindowCalculator {

  def getMinTimeAdWindow(input: List[String], logs: List[Log]): Option[MinTimeAdWindow] = {
    val logsHavingAdIds = logs.filter(x => input.contains(x.adID))
    if (isAllAdIdAvailableInLog(input, logsHavingAdIds)) {
      getPossibleTimeWindows(input, logsHavingAdIds).map {
        logsContainingInputAds => calculateTime(logsContainingInputAds.sortBy(log => log.timeStamp))
      }.filter(_.isDefined).sortBy(_.get.duration).head
    } else
      None
  }

  private def calculateTime(logs: List[Log]): Option[MinTimeAdWindow] = {
    logs match {
      case firstLog :: otherLogs =>
        val endTime = otherLogs.last.timeStamp
        val startTime = firstLog.timeStamp
        Some(models.MinTimeAdWindow(startTime, endTime, ((endTime.getTime - startTime.getTime) / 1000 / 60)))
      case _ => None
    }
  }

  @tailrec
  private def getPossibleTimeWindows(input: List[String], logs: List[Log], res: List[List[Log]] = List.empty): List[List[Log]] = {
    if (isAllAdIdAvailableInLog(input, logs.take(input.length))) {
      if (logs.tail.tail.isEmpty || logs.length < input.length) {
        List(res.headOption.getOrElse(Nil), logs)
      } else {
        getPossibleTimeWindows(input, logs.tail, List(logs, res.headOption.getOrElse(Nil)))
      }
    } else {
      res
    }
  }

  private def isAllAdIdAvailableInLog(input: List[String], logs: List[Log]): Boolean = {
    val adsInList = logs.map(x => x.adID)
    input.diff(adsInList).isEmpty && logs.nonEmpty
  }
}
