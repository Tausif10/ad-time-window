package readers

import models.Log
import transformers.LogTransformer

import scala.util.Try

class FileLogReader(logTransformer: LogTransformer) extends LogReader {

  override def read(): Try[List[Log]] = {
    Try {
      val filePath = "src/main/scala/resources/LogRepo"
      val logs = scala.io.Source.fromFile(filePath).getLines().toList
      logTransformer.transform(logs)
    }
  }
}
