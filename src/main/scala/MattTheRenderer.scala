import Colouring._
import doodle.core.{Color, Image}
import doodle.core.Image._
import doodle.core.PathElement.{lineTo, moveTo}
import doodle.core.Point.cartesian

object MattTheRenderer {


  sealed trait TriangleOrientation

  case object TL extends TriangleOrientation

  case object BR extends TriangleOrientation

  case object BL extends TriangleOrientation

  case object TR extends TriangleOrientation


  def isUpperRow(index: Int, canvasLayout: CanvasLayout): Boolean = {
    (index / (4 * canvasLayout.diRecColumns)) % 2 == 0
  }

  def getOrientation(index: Int, canvasLayout: CanvasLayout): TriangleOrientation = {

    if (isUpperRow(index, canvasLayout))
      getTopRowOrientation(index)
    else
      getTopRowOrientation(index + 2)
  }

  def getTopRowOrientation(index: Int): TriangleOrientation = {
    index % 4 match {
      case 0 => TL
      case 1 => BR
      case 2 => BL
      case 3 => TR
    }
  }


  def render(canvasLayout: CanvasLayout,
             triangleDimensions: TriangleDimensions): Image = {

    val colours = getColours(canvasLayout)
    val triangles = getTriangles(canvasLayout, triangleDimensions, colours)
    val rows: List[List[Image]] = triangles.grouped(canvasLayout.diRecColumns * 4).toList
    val rowsAsImages: List[Image] = rows.map(joinTriangleRow)

    rowsAsImages.fold(Image.empty)(_ above _)
  }


  def joinTriangleRow(images: List[Image]): Image = {
    val first = images.head
    images.tail.zipWithIndex.foldLeft(first)(joinImages)
  }

  def joinImages(accImage: Image, elem: (Image, Int)): Image = {
    val (triangle, index) = elem
    if (index % 2 == 1) {
      accImage beside triangle
    } else {
      accImage on triangle
    }
  }

  def getTriangles(canvasLayout: CanvasLayout,
                   triangleDimensions: TriangleDimensions,
                   colours: List[Color]): List[Image] = {
    colours
      .zipWithIndex
      .map { case (colour, index) => (colour, getOrientation(index, canvasLayout)) }
      .map { case (colour, index) => (colour, makeTriangle(index, triangleDimensions)) }
      .map { case (colour, image) => image.fillColor(colour) }
  }


  def makeTriangle(orientation: TriangleOrientation, tp: TriangleDimensions): Image = {

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

    def makeBottomLeft: Image = closedPath(List(
      lineTo(cartesian(0, tp.height)),
      lineTo(cartesian(tp.width, 0)),
      lineTo(cartesian(0, 0)))).
      lineWidth(tp.lineWidth)

    def makeTopRight: Image = closedPath(List(
      moveTo(cartesian(0, tp.height)),
      lineTo(cartesian(tp.width, tp.height)),
      lineTo(cartesian(tp.width, 0)),
      lineTo(cartesian(0, tp.height)))).
      lineWidth(tp.lineWidth)

    orientation match {
      case TL => makeTopLeft
      case BR => makeBottomRight
      case BL => makeBottomLeft
      case TR => makeTopRight
    }
  }


}


