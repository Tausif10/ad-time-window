package transformers

import models.Log
import readers.LogReader
import utils.DateUtils

class LogTransformer {

  def transform(logs: List[String]): List[Log] = {
    logs.filter(_.split(',').length == 4).map { log =>
      val logDetails = log.split(',').toList
      val imprId :: adId :: userId :: dateTime = logDetails
        Log(imprId.trim, adId.trim, userId.trim, DateUtils.convertToDate(dateTime.headOption.getOrElse("").trim))
    }
  }
}
