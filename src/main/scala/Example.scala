import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

case class TriangleDimensions(width: Int, height: Int, lineWidth: Double)

case class CanvasLayout(diRecColumns: Int, diRecRows: Int)

object Example {

  val triangleProperties = TriangleDimensions(30, 80, 0.1)
  val canvasLayout = CanvasLayout(8,3)
  val image = MattTheRenderer.render(canvasLayout, triangleProperties)

  def main(args: Array[String]): Unit = {
    image.draw
  }
}
