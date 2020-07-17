import java.io.FileWriter
import java.io.PrintWriter

fun visualize(svgFileName: String, all: BitMap, elements: List<BitMap>, cellSize: Int = 20) {
    val style = "paint-order: stroke; fill: white; stroke: black; stroke-width: 2px; font:${cellSize * 2}px bold sans;"

    PrintWriter(FileWriter(svgFileName)).use { output ->
        output.println("""<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="${all.width * cellSize}" height="${all.height * cellSize}">""")
        output.println("""<rect width="${all.width * cellSize}" height="${all.height * cellSize}" style="fill:black"/>""")

        fun point(x: Int, y: Int) {
            output.println("""<rect x="${x * cellSize}" y="${y * cellSize}" width="7" height="7" style="fill:white"/>""")
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
            val (text, color) = parseGlyph(element)?.let {
                when (it) {
                    is Number -> it.value.toString() to "green"
                    is Variable -> "x" + it.index to "blue"
                    is EqualitySign -> "=" to "yellow"
                    is ApplyGlyph -> "ap" to "yellow"
                    is Operator.Negate -> "neg" to "yellow"
                    is Operator.IntegerDivision -> "/" to "yellow"
                    is Operator.Product -> "*" to "yellow"
                    is Operator.Sum -> "+" to "yellow"
                    is Operator.Dec -> "dec" to "yellow"
                    is Operator.Inc -> "inc" to "yellow"
                    is BooleanGlyph.False -> "false" to "yellow"
                    is BooleanGlyph.True -> "true" to "yellow"
                    is Comparision.LessThan -> "&lt;" to "yellow"
                    is Comparision.Equals -> "==" to "yellow"
                    is Reference -> "r" + it.index to "red"
                    is CommunicateAction -> "com" to "pink"
                    else -> throw IllegalArgumentException("unknown glyph")
                }
            } ?: "" to "gray"

            annotate(element.columnOffset, element.rowOffset, element.width, element.height, color, text)
        }

        output.println("""</svg>""")
    }
}

