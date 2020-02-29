package readers

import models.Log

trait LogReader {
  def read(): List[Log]
}
