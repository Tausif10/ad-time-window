package utils

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {
  val DATE_FORMAT = "hh:mma MMM dd"

  def convertToDate(stringToBeDate: String, dateFormat: String = DATE_FORMAT):Date = {
      val simpleDateFormat = new SimpleDateFormat(dateFormat)
      simpleDateFormat.parse(stringToBeDate)
  }
}
