import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._
import doodle.core.PathElement.{lineTo, moveTo}
import doodle.core.Point.cartesian

case class TriangleProperties(width: Int, height: Int, lineWidth: Double)

case class CanvasLayout(diRecColumns: Int, diRecRows: Int)


case class DiRecRenderer(tp: TriangleProperties) {

  def makeBottomRight: Image = closedPath(List(
    lineTo(cartesian(tp.width, tp.height)),
    lineTo(cartesian(tp.width, 0)),
    lineTo(cartesian(0, 0)))).
    lineWidth(tp.lineWidth)

  def makeTopLeft: Image = closedPath(List(
    lineTo(cartesian(0, tp.height)),
    lineTo(cartesian(tp.width, tp.height)),
    lineTo(cartesian(0, 0)))).
    lineWidth(tp.lineWidth)

  def makeTopRight: Image = closedPath(List(
    moveTo(cartesian(0, tp.height)),
    lineTo(cartesian(tp.width, tp.height)),
    lineTo(cartesian(tp.width, 0)),
    lineTo(cartesian(0, tp.height)))).
    lineWidth(tp.lineWidth)

  def makeBottomLeft: Image = closedPath(List(
    lineTo(cartesian(0, tp.height)),
    lineTo(cartesian(tp.width, 0)),
    lineTo(cartesian(0, 0)))).
    lineWidth(tp.lineWidth)
}



object Example {

  val triangleProperties = TriangleProperties(30, 80, 0.1)
  val canvasLayout = CanvasLayout(8, 3)
  val canvas: Canvas = Canvas.blank(canvasLayout, triangleProperties)

  def main(args: Array[String]): Unit = {
    canvas.toImage.draw
  }
}
