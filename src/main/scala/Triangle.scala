import doodle.core.{Angle, Normalized}

trait DiRec {
  val bottomRec =

}

trait Triangle {
  val height: Int
  val width: Int
  val orientation: Orientation
}

trait ColouredTriangle extends Triangle with HSLColour

trait HSLColour {
  val hue: Angle
  val saturation: Normalized
  val lightness: Normalized
}


sealed trait Orientation

case object BL extends Orientation

case object BR extends Orientation

case object TL extends Orientation

case object TR extends Orientation

