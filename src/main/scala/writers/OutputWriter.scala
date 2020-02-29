package writers

import models.MinTimeAdWindow

trait OutputWriter {
  def write(minTimeAdWindow: MinTimeAdWindow): String
}
