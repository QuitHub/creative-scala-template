import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._
object Example {


  import doodle.core.Point._
  import doodle.core.PathElement._
  
  
  def makeFullDiamond(height: Int, width: Int, lineWidth: Double): Image = {
    val bottomLeftTriangle =
      closedPath(List(
        lineTo(cartesian(0, height)),
        lineTo(cartesian(width, 0)),
        lineTo(cartesian(0, 0)))).
        lineWidth(lineWidth)


    val topRightTriangle =
      closedPath(List(
        moveTo(cartesian(0, height)),
        lineTo(cartesian(width, height)),
        lineTo(cartesian(width, 0)),
        lineTo(cartesian(0, height)))).
        lineWidth(lineWidth)

    val topLeftTriangle =
      closedPath(List(
        lineTo(cartesian(width, height)),
        lineTo(cartesian(width, 0)),
        lineTo(cartesian(0, 0)))).
        lineWidth(lineWidth)


    val bottomRightTriangle =
      closedPath(List(
        lineTo(cartesian(0, height)),
        lineTo(cartesian(width, height)),
        lineTo(cartesian(0, 0)))).
        lineWidth(lineWidth)


    val zerothRec =
      bottomLeftTriangle on topRightTriangle

    val firstRec = topLeftTriangle on bottomRightTriangle

    val upperHalfDiamond = firstRec beside zerothRec

    val lowerHalfDiamond = zerothRec beside firstRec

    upperHalfDiamond above lowerHalfDiamond
  }


  val fullDiamond = makeFullDiamond(100, 20, 0.1)

  val row = fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond

  val painting = row above row

  def main(args: Array[String]): Unit = {
    painting.draw
  }
}
