import doodle.core.Image

case class Canvas(diRecs: List[DiRec], layout: CanvasLayout) {

  def toImage: Image = {
    val rowsOfDiRec: Iterator[List[DiRec]] = diRecs.grouped(layout.diRecColumns)
    val rowsOfImages: Iterator[Image] = rowsOfDiRec.map(row => row.foldLeft(Image.empty) ((acc, cur) => acc beside cur.toImage))
    rowsOfImages.foldLeft(Image.empty)((acc, cur) => acc above cur)
  }
}

object Canvas {
  def blank(layout: CanvasLayout, triangleProperties: TriangleProperties): Canvas = {
    val foo: Seq[DiRec] = for {
      _ <- 1 to layout.diRecRows
      _ <- 1 to layout.diRecColumns
    } yield DiRec(triangleProperties)
    Canvas(foo.toList, layout)
  }
}



