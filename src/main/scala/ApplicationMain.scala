import transformers.LogTransformer
import readers.{InputAdIdReader, FileLogReader}
import services.{LogProcessor, AdTimeWindowCalculator}
import writers.TimeWindowOutputWriter

object ApplicationMain {

  def main(args: Array[String]): Unit = {
    val inputAdIdReader = new InputAdIdReader()
    val logTransformer = new LogTransformer()
    val logReader = new FileLogReader(logTransformer)
    val timeWindowCalculator = new AdTimeWindowCalculator()
    val outputFormatter = new TimeWindowOutputWriter
    val logProcessor = new LogProcessor(logReader, timeWindowCalculator, outputFormatter)
    println(logProcessor.process(inputAdIdReader.read))
  }
}