import Colouring.getColours
import doodle.backend.StandardInterpreter._
import doodle.core.Image
import doodle.jvm.Java2DFrame._
import doodle.syntax._

case class TriangleDimensions(width: Int, height: Int, lineWidth: Double)

case class CanvasLayout(diRecColumns: Int, diRecRows: Int)

object Example {

  import MattTheRenderer._

  val triangleProperties = TriangleDimensions(60, 160, 0)
  val canvasLayout = CanvasLayout(8, 3)

  val layers: List[Image] = (0 to 2000)
    .toList
    .map(_ => getColours(canvasLayout))
    .map(render(canvasLayout, triangleProperties, _))

  val image = layers.reduce(_ on _)

  def main(args: Array[String]): Unit = {

    image.draw

  }
}
