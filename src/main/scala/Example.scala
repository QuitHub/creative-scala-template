import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._
object Example {


  import doodle.core.Point._
  import doodle.core.PathElement._
  val bottomLeftTriangle =
    closedPath(List(
      lineTo(cartesian(0, 100)),
      lineTo(cartesian(20, 0)),
      lineTo(cartesian(0, 0)))).
      lineWidth(0.1)


  val topRightTriangle =
    closedPath(List(
      moveTo(cartesian(0, 100)),
      lineTo(cartesian(20, 100)),
      lineTo(cartesian(20, 0)),
      lineTo(cartesian(0, 100)))).
      lineWidth(0.1)

  val topLeftTriangle =
    closedPath(List(
      lineTo(cartesian(20, 100)),
      lineTo(cartesian(20, 0)),
      lineTo(cartesian(0, 0)))).
      lineWidth(0.1)


  val bottomRightTriangle =
    closedPath(List(
      lineTo(cartesian(0, 100)),
      lineTo(cartesian(20, 100)),
      lineTo(cartesian(0, 0)))).
      lineWidth(0.1)


  val zerothRec =
    bottomLeftTriangle on topRightTriangle

  val firstRec = topLeftTriangle on bottomRightTriangle

  val upperHalfDiamond = firstRec beside zerothRec

  val lowerHalfDiamond = zerothRec beside firstRec

  val fullDiamond = upperHalfDiamond above lowerHalfDiamond


  val row = fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond beside fullDiamond

  val painting = row above row

  def main(args: Array[String]): Unit = {
    painting.draw
  }
}
