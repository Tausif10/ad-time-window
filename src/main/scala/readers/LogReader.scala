package readers

import models.Log

import scala.util.Try

trait LogReader {
  def read(): Try[List[Log]]
}
