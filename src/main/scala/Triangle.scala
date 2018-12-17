import doodle.core.{Angle, Normalized}

import scala.collection.immutable.List

trait Triangle {
  val height: Int
  val width: Int
}

object Triangle {
  //TODO get the triangle orientation based on some form of index
  def orientation(layout: List[Triangle], triangleCountXy: TriangleCountXY): Orientation = {
    BL
  }
}

object Test {
  def setupCanvass(triangleHeight: Int, triangleWidth: Int, triangleCountXy: TriangleCountXY) = ???

}


trait ColouredTriangle extends Triangle with HSLColour

trait HSLColour {
  val hue: Angle
  val saturation: Normalized
  val lightness: Normalized
}




case class TriangleCountXY(triangleCountX: Int, triangleCountY: Int) {
  def apply(triangleCountX: Int, triangleCountY: Int): TriangleCountXY = {

    new TriangleCountXY(triangleCountX, triangleCountY)
  }
}

sealed trait Orientation

case object BL extends Orientation

case object BR extends Orientation

case object TL extends Orientation

case object TR extends Orientation

