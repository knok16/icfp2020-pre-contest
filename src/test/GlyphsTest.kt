import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GlyphsTest {
    @Test
    fun parseNumber() {
        assertParseNumber(
            0, arrayOf(
                booleanArrayOf(false, true),
                booleanArrayOf(true, false)
            )
        )
        assertParseNumber(
            1, arrayOf(
                booleanArrayOf(false, true),
                booleanArrayOf(true, true)
            )
        )
        assertParseNumber(
            2, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            3, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            4, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, false, false),
                booleanArrayOf(true, true, false)
            )
        )
        assertParseNumber(
            5, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, false),
                booleanArrayOf(true, true, false)
            )
        )
        assertParseNumber(
            6, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, true, false)
            )
        )
        assertParseNumber(
            7, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, true, false)
            )
        )
        assertParseNumber(
            15, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, true, true)
            )
        )
        assertParseNumber(
            511, arrayOf(
                booleanArrayOf(false, true, true, true),
                booleanArrayOf(true, true, true, true),
                booleanArrayOf(true, true, true, true),
                booleanArrayOf(true, true, true, true)
            )
        )
        assertParseNumber(
            512, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false)
            )
        )
        assertParseNumber(
            513, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, true, false, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false)
            )
        )
        assertParseNumber(
            514, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false)
            )
        )
        assertParseNumber(
            65535, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, true, true, true, true)
            )
        )
    }

    @Test
    fun parseNegativeNumber() {
        assertParseNumber(
            -1, arrayOf(
                booleanArrayOf(false, true),
                booleanArrayOf(true, true),
                booleanArrayOf(true, false)
            )
        )
        assertParseNumber(
            -2, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, false, false),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -3, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, false, false),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -4, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, false, false),
                booleanArrayOf(true, true, false),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -5, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, false),
                booleanArrayOf(true, true, false),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -6, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, false, true),
                booleanArrayOf(true, true, false),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -7, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, true, false),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -15, arrayOf(
                booleanArrayOf(false, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, true, true),
                booleanArrayOf(true, false, false)
            )
        )
        assertParseNumber(
            -511, arrayOf(
                booleanArrayOf(false, true, true, true),
                booleanArrayOf(true, true, true, true),
                booleanArrayOf(true, true, true, true),
                booleanArrayOf(true, true, true, true),
                booleanArrayOf(true, false, false, false)
            )
        )
        assertParseNumber(
            -512, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, false, false, false)
            )
        )
        assertParseNumber(
            -513, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, true, false, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, false, false, false)
            )
        )
        assertParseNumber(
            -514, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, true, false, false),
                booleanArrayOf(true, false, false, false, false),
                booleanArrayOf(true, false, false, false, false)
            )
        )
        assertParseNumber(
            -65535, arrayOf(
                booleanArrayOf(false, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, true, true, true, true),
                booleanArrayOf(true, false, false, false, false)
            )
        )
    }

    @Test
    fun parseNumberNotSquare() {
        assertParseNumber(
            0, arrayOf(
                booleanArrayOf(false, true, false),
                booleanArrayOf(true, false, true)
            )
        )
        assertParseNumber(
            1, arrayOf(
                booleanArrayOf(false, true, true, false, false, false, false, true),
                booleanArrayOf(true, false, false, true, true, true, true, false)
            )
        )
        assertParseNumber(
            -1, arrayOf(
                booleanArrayOf(true, false, true, false, false, false, false, true),
                booleanArrayOf(false, true, false, true, true, true, true, false)
            )
        )
        assertParseNumber(
            2, arrayOf(
                booleanArrayOf(false, true, true, false, false, false, true, false),
                booleanArrayOf(true, false, false, true, true, true, false, true)
            )
        )
        assertParseNumber(
            -2, arrayOf(
                booleanArrayOf(true, false, true, false, false, false, true, false),
                booleanArrayOf(false, true, false, true, true, true, false, true)
            )
        )
    }

    @Test
    fun parseApplyGlyph() {
        assertEquals(
            Combinator.Apply, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true),
                        booleanArrayOf(true, false)
                    )
                )
            )
        )
    }


    @Test
    fun parseEqualityGlyph() {
        assertEquals(
            EqualitySign, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true),
                        booleanArrayOf(true, false, false),
                        booleanArrayOf(true, true, true)
                    )
                )
            )
        )
    }

    @Test
    fun parseSuccessorGlyph() {
        assertEquals(
            Operator.Inc, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, true, false, false),
                        booleanArrayOf(true, false, false, true),
                        booleanArrayOf(true, false, true, true)
                    )
                )
            )
        )
    }

    @Test
    fun parsePredecessorGlyph() {
        assertEquals(
            Operator.Dec, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, true, false, false),
                        booleanArrayOf(true, false, true, false),
                        booleanArrayOf(true, false, true, true)
                    )
                )
            )
        )
    }

    @Test
    fun parseSumGlyph() {
        assertEquals(
            Operator.Sum, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, true, false, true),
                        booleanArrayOf(true, true, false, true),
                        booleanArrayOf(true, true, false, true)
                    )
                )
            )
        )
    }

    @Test
    fun parseProductGlyph() {
        assertEquals(
            Operator.Product, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, false, true, false),
                        booleanArrayOf(true, false, true, false),
                        booleanArrayOf(true, false, true, false)
                    )
                )
            )
        )
    }

    @Test
    fun parseIntegerDivisionGlyph() {
        assertEquals(
            Operator.IntegerDivision, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, false, false, false),
                        booleanArrayOf(true, true, false, true),
                        booleanArrayOf(true, false, false, false)
                    )
                )
            )
        )
    }

    @Test
    fun parseFalseConstant() {
        assertEquals(
            Combinator.notK, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true),
                        booleanArrayOf(true, false, false),
                        booleanArrayOf(true, false, true)
                    )
                )
            )
        )
    }

    @Test
    fun parseTrueConstant() {
        assertEquals(
            Combinator.K, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true),
                        booleanArrayOf(true, false, true),
                        booleanArrayOf(true, false, false)
                    )
                )
            )
        )
    }

    @Test
    fun parseEqualsGlyph() {
        assertEquals(
            Comparision.Equals, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, false, false, false),
                        booleanArrayOf(true, false, false, false),
                        booleanArrayOf(true, true, true, true)
                    )
                )
            )
        )
    }

    @Test
    fun parseStrictLessGlyph() {
        assertEquals(
            Comparision.LessThan, parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, false, false, false),
                        booleanArrayOf(true, false, false, true),
                        booleanArrayOf(true, false, true, true)
                    )
                )
            )
        )
    }

    @Test
    fun parseVariable() {
        assertEquals(
            Variable(0), parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, true, false, true),
                        booleanArrayOf(true, false, true, true),
                        booleanArrayOf(true, true, true, true)
                    )
                )
            )
        )
        assertEquals(
            Variable(1), parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true),
                        booleanArrayOf(true, true, false, true),
                        booleanArrayOf(true, false, false, true),
                        booleanArrayOf(true, true, true, true)
                    )
                )
            )
        )
        assertEquals(
            Variable(2), parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true, true),
                        booleanArrayOf(true, true, false, false, true),
                        booleanArrayOf(true, false, true, false, true),
                        booleanArrayOf(true, false, true, true, true),
                        booleanArrayOf(true, true, true, true, true)
                    )
                )
            )
        )
        assertEquals(
            Variable(3), parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true, true),
                        booleanArrayOf(true, true, false, false, true),
                        booleanArrayOf(true, false, false, false, true),
                        booleanArrayOf(true, false, true, true, true),
                        booleanArrayOf(true, true, true, true, true)
                    )
                )
            )
        )
        assertEquals(
            Variable(4), parseGlyph(
                bitMapFrom(
                    arrayOf(
                        booleanArrayOf(true, true, true, true, true),
                        booleanArrayOf(true, true, false, false, true),
                        booleanArrayOf(true, false, true, true, true),
                        booleanArrayOf(true, false, false, true, true),
                        booleanArrayOf(true, true, true, true, true)
                    )
                )
            )
        )
    }

    private fun assertParseNumber(value: Long, array: Array<BooleanArray>) {
        assertEquals(Number(value), parseGlyph(bitMapFrom(array), emptyMap()))
    }
}