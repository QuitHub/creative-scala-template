case class Triangle(height: Int, width: Int, orientation: Orientation)

sealed trait Orientation

case object BL extends Orientation
case object BR extends Orientation
case object TL extends Orientation
case object TR extends Orientation

