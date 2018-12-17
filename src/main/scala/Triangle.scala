import doodle.core.{Angle, Image, Normalized}

case class CanvassLayout(diRecColumns: Int, diRecRows: Int)

case class Canvass(diRecs: List[UncolouredDiRec], layout: CanvassLayout) {

  def toImage: Image = {
    val rowsOfDiRec: Iterator[List[UncolouredDiRec]] = diRecs.grouped(layout.diRecColumns)
    val rowsOfImages: Iterator[Image] = rowsOfDiRec.map(row => row.foldLeft(Image.empty) ((acc, cur) => acc beside cur.toImage))
    rowsOfImages.foldLeft(Image.empty)((acc, cur) => acc above cur)
  }
}

object Canvass {
  def blank(layout: CanvassLayout, triangleProperties: TriangleProperties): Canvass = {
    val foo: Seq[UncolouredDiRec] = for {
      _ <- 1 to layout.diRecRows
      _ <- 1 to layout.diRecColumns
    } yield UncolouredDiRec(triangleProperties)
    Canvass(foo.toList, layout)
  }
}

trait HSLColour {
  val hue: Angle
  val saturation: Normalized
  val lightness: Normalized
}

