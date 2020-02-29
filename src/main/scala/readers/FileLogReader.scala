package readers

import models.Log
import transformers.LogTransformer

class FileLogReader(logTransformer: LogTransformer) extends LogReader {

  override def read(): List[Log] = {
    val filePath = "src/main/scala/resources/LogRepo"
    val logs = scala.io.Source.fromFile(filePath).getLines().toList
    logTransformer.transform(logs)
  }
}
