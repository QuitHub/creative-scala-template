import doodle.core.{Angle, Color, Normalized}
import doodle.syntax.uByte._

object Colouring {

  val palette: Set[Color] = {
    val aliceBlue            = Color.rgb(0xf0.uByte, 0xf8.uByte, 0xff.uByte)
    val antiqueWhite         = Color.rgb(0xfa.uByte, 0xeb.uByte, 0xd7.uByte)
    val aqua                 = Color.rgb(0x00.uByte, 0xff.uByte, 0xff.uByte)
    val aquamarine           = Color.rgb(0x7f.uByte, 0xff.uByte, 0xd4.uByte)
    val azure                = Color.rgb(0xf0.uByte, 0xff.uByte, 0xff.uByte)
    val beige                = Color.rgb(0xf5.uByte, 0xf5.uByte, 0xdc.uByte)
    val bisque               = Color.rgb(0xff.uByte, 0xe4.uByte, 0xc4.uByte)
    val black                = Color.rgb(0x00.uByte, 0x00.uByte, 0x00.uByte)
    val blanchedAlmond       = Color.rgb(0xff.uByte, 0xeb.uByte, 0xcd.uByte)
    val blue                 = Color.rgb(0x00.uByte, 0x00.uByte, 0xff.uByte)
    val blueViolet           = Color.rgb(0x8a.uByte, 0x2b.uByte, 0xe2.uByte)

    Set(aliceBlue, antiqueWhite, aqua, aquamarine, azure, beige, bisque, black, blanchedAlmond, blue, blueViolet)
  }

  def getColours(canvasLayout: CanvasLayout): List[Color] = {
    (0 until canvasLayout.diRecColumns * canvasLayout.diRecRows * 8).map(_ => getRandomColour).toList
  }

  def getRandomColour: Color = {
    val r = scala.util.Random
    val num = r.nextInt(11)
    palette.toList(num)
  }
}


trait HSLColour {
  val hue: Angle
  val saturation: Normalized
  val lightness: Normalized
}