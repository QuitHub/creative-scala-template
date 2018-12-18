import doodle.core.Image

case class DiRec(
                  topRightInner: Image,
                  topRightOuter: Image,
                  topLeftOuter: Image,
                  topLeftInner: Image,
                  bottomLeftOuter: Image,
                  bottomLeftInner: Image,
                  bottomRightInner: Image,
                  bottomRightOuter: Image
                ) {

  def getTriangle(index: Int): Option[Image] ={
    index match {
      case 0 => Some(topLeftOuter)
      case 1 => Some(topLeftInner)
      case 2 => Some(topRightInner)
      case 3 => Some(topRightOuter)
      case 4 => Some(bottomLeftOuter)
      case 5 => Some(bottomLeftInner)
      case 6 => Some(bottomRightInner)
      case 7 => Some(bottomRightOuter)
      case _ => None
    }
  }

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


object DiRec {

  def apply(triangleProperties: TriangleProperties): DiRec = {

    val drawer = DiRecRenderer(triangleProperties)

    val topRightInner: Image = drawer.makeBottomLeft

    val topRightOuter: Image = drawer.makeTopRight

    val topLeftOuter: Image = drawer.makeTopLeft

    val topLeftInner: Image = drawer.makeBottomRight

    val bottomLeftOuter: Image = drawer.makeBottomLeft

    val bottomLeftInner: Image = drawer.makeTopRight

    val bottomRightInner: Image = drawer.makeTopLeft

    val bottomRightOuter: Image = drawer.makeBottomRight

    DiRec(topRightInner, topRightOuter, topLeftOuter, topLeftInner, bottomLeftOuter, bottomLeftInner, bottomRightInner, bottomRightOuter)
  }
}