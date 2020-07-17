import java.io.FileWriter
import java.io.PrintWriter

fun visualize(svgFileName: String, all: BitMap, elements: List<BitMap>, cellSize: Int = 20) {
    val style = "paint-order: stroke; fill: white; stroke: black; stroke-width: 2px; font:${cellSize * 2}px bold sans;"

    PrintWriter(FileWriter(svgFileName)).use { output ->
        output.println("""<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="${all.width * cellSize}" height="${all.height * cellSize}">""")
        output.println("""<rect width="${all.width * cellSize}" height="${all.height * cellSize}" style="fill:black"/>""")

        fun point(x: Int, y: Int) {
            output.println("""<rect x="${x * cellSize}" y="${y * cellSize}" width="${cellSize - 1}" height="${cellSize - 1}" style="fill:white"/>""")
        }

        fun annotate(x: Int, y: Int, width: Int, height: Int, color: String, text: String) {
            output.println("""<rect x="${x * cellSize}" y="${y * cellSize}" width="${width * cellSize}" height="${height * cellSize}" style="fill:${color};opacity:0.5"/>""")
            output.println("""<text x="${x * cellSize + width * cellSize / 2}" y="${y * cellSize + height * cellSize / 2}" dominant-baseline="middle" text-anchor="middle" fill="white" style="$style">$text</text>""")
        }

        for (x in 0 until all.width)
            for (y in 0 until all.height)
                if (all[x, y])
                    point(x, y)


        elements.forEach { element ->
            val glyph = parseGlyph(element)

            val text = glyph?.shortName ?: ""
            val color = when (glyph) {
                is Number -> "green"
                is Variable -> "blue"
                is EqualitySign -> "yellow"
                is ApplyGlyph -> "yellow"
                is Operator.Negate -> "yellow"
                is Operator.IntegerDivision -> "yellow"
                is Operator.Product -> "yellow"
                is Operator.Sum -> "yellow"
                is Operator.Dec -> "yellow"
                is Operator.Inc -> "yellow"
                is BooleanGlyph.False -> "yellow"
                is BooleanGlyph.True -> "yellow"
                is Comparision.LessThan -> "yellow"
                is Comparision.Equals -> "yellow"
                is Reference -> "red"
                is CommunicateAction -> "pink"
                else -> throw IllegalArgumentException("unknown glyph")
            }

            annotate(element.columnOffset, element.rowOffset, element.width, element.height, color, text)
        }

        output.println("""</svg>""")
    }
}

