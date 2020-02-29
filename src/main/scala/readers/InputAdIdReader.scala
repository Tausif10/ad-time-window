package readers

class InputAdIdReader extends InputReader {
  override def read() = {
    scala.io.StdIn.readLine("Input Ad Ids[comma separated]:")
  }
}
