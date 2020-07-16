interface Glyph

data class Number(val value: Long) : Glyph

interface BooleanGlyph : Glyph {
    object False : BooleanGlyph
    object True : BooleanGlyph
}

object EqualitySign : Glyph
object ApplyGlyph : Glyph

interface Operator : Glyph {
    object IntegerDivision : Operator
    object Product : Operator
    object Sum : Operator
    object Dec : Operator
    object Inc : Operator
}

interface Comparision : Glyph {
    object LessThan : Comparision
    object Equals : Comparision
}

data class Variable(val index: Long) : Glyph

val defaultGlyphRepository: Map<Long, Glyph> = mapOf(
    0L to ApplyGlyph,
    12L to EqualitySign,
    // constants
    2L to BooleanGlyph.True,
    8L to BooleanGlyph.False,
    // operators
    40L to Operator.IntegerDivision,
    146L to Operator.Product,
    365L to Operator.Sum,
    401L to Operator.Dec,
    417L to Operator.Inc,
    // comparisons
    416L to Comparision.LessThan,
    448L to Comparision.Equals
)

fun parseGlyph(bitMap: BitMap, glyphRepository: Map<Long, Glyph> = defaultGlyphRepository): Glyph? {
    return if ((1 until bitMap.width).all { column -> bitMap[column, 0] } &&
        (1 until bitMap.height).all { row -> bitMap[0, row] }) {

        val isNumber = !bitMap[0, 0]

        val numberValue = (1 until bitMap.height).flatMap { row ->
            (1 until bitMap.width).map { column -> row to column }
        }.foldIndexed(0L) { index, acc, (row, column) ->
            acc or if (bitMap[column, row]) 1L shl index else 0L
        }

        if (isNumber) {
            when (bitMap.height - bitMap.width) {
                0 -> Number(numberValue)
                1 -> {
                    if ((1 until bitMap.width).none { column -> bitMap[column, bitMap.height - 1] })
                        Number(-numberValue)
                    else
                        null
                }
                else -> null
            }
        } else {
            if (bitMap.width != bitMap.height)
                null
            else if (numberValue in glyphRepository) {
                glyphRepository.getValue(numberValue)
            } else {
                val isVariable = bitMap[1, 1] &&
                        (1 until bitMap.width).all { column -> bitMap[column, bitMap.height - 1] } &&
                        (1 until bitMap.height).all { row -> bitMap[bitMap.width - 1, row] } &&
                        (2 until bitMap.width - 1).none { column -> bitMap[column, 1] } &&
                        (2 until bitMap.height - 1).none { row -> bitMap[1, row] }

                if (isVariable) {
                    val indexValue = (2 until bitMap.height - 1).flatMap { row ->
                        (2 until bitMap.width - 1).map { column -> row to column }
                    }.foldIndexed(0L) { index, acc, (row, column) ->
                        acc or if (bitMap[column, row]) 0L else 1L shl index
                    }

                    Variable(indexValue)
                } else {
                    null
                }
            }
        }
    } else {
        if (bitMap.height == 2 && bitMap.width >= 3) {
            if ((0 until bitMap.width).all { i -> bitMap[i, 0] != bitMap[i, 1] }) {
                val bits = (0 until bitMap.width).map { i -> bitMap[i, 0] }
                val sign = when {
                    !bits[0] && bits[1] -> 1
                    bits[0] && !bits[1] -> -1
                    else -> return null
                }

                var t = 2
                while (t < bitMap.width && bits[t]) t++

                val length = 4 * (t - 2)
                if (t + length + 1 == bitMap.width) {
                    val abs = (0 until length).filter { bits[bits.size - it - 1] }.fold(0L) { acc, position ->
                        acc or (1L shl position)
                    }
                    Number(sign * abs)
                } else {
                    null
                }
            } else {
                null
            }
        } else {
            null
        }
    }
}
