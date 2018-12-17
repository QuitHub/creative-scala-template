import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._
import doodle.core.PathElement.{lineTo, moveTo}
import doodle.core.Point.cartesian

case class TriangleProperties(width: Int, height: Int, lineWidth: Double)

case class DiRecDrawer(tp: TriangleProperties) {

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

trait DiRec {

  def topRightInner: Image

  def topRightOuter: Image

  def topLeftOuter: Image

  def topLeftInner: Image

  def bottomLeftOuter: Image

  def bottomLeftInner: Image

  def bottomRightInner: Image

  def bottomRightOuter: Image
}

case class UncolouredDiRec(
                            topRightInner: Image,
                            topRightOuter: Image,
                            topLeftOuter: Image,
                            topLeftInner: Image,
                            bottomLeftOuter: Image,
                            bottomLeftInner: Image,
                            bottomRightInner: Image,
                            bottomRightOuter: Image
                          ){
  val toImage: Image = {

    val topLeftRec = topLeftOuter on topLeftInner
    val topRightRec = topRightOuter on topRightInner
    val bottomLeftRec = bottomLeftOuter on bottomLeftInner
    val bottomRightRec = bottomRightOuter on bottomRightInner
    val topHalfDiamond = topLeftRec beside topRightRec
    val bottomHalfDiamond = bottomLeftRec beside bottomRightRec
    topHalfDiamond above bottomHalfDiamond
  }
}


object UncolouredDiRec {

  def apply(triangleProperties: TriangleProperties): UncolouredDiRec = {

    val drawer = DiRecDrawer(triangleProperties)

    val topRightInner: Image = drawer.makeBottomLeft

    val topRightOuter: Image = drawer.makeTopRight

    val topLeftOuter: Image = drawer.makeTopLeft

    val topLeftInner: Image = drawer.makeBottomRight

    val bottomLeftOuter: Image = drawer.makeBottomLeft

    val bottomLeftInner: Image = drawer.makeTopRight

    val bottomRightInner: Image = drawer.makeTopLeft

    val bottomRightOuter: Image = drawer.makeBottomRight
    new UncolouredDiRec(topRightInner, topRightOuter, topLeftOuter, topLeftInner, bottomLeftOuter, bottomLeftInner, bottomRightInner, bottomRightOuter)
  }
}

object Example {

  val triangleProperties = TriangleProperties(30, 80, 0.1)
  val canvasLayout = CanvassLayout(8, 3)
  val canvass: Canvass = Canvass.blank(canvasLayout, triangleProperties)

  def main(args: Array[String]): Unit = {
    canvass.toImage.draw
  }
}
