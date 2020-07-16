import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class BitMapTest {
    private val array = Array(20) { row -> BooleanArray(15) { column -> (row + column) % 2 == 1 } }

    @Test
    fun getBit() {
        val bitMap = BitMap(8, 9, array, 1, 2)

        for (row in 0..8)
            for (column in 0..7)
                assertEquals((row + column) % 2 == 0, bitMap[column, row])
    }

    @Test
    fun rangeChecks() {
        val bitMap = BitMap(8, 9, array, 1, 2)

        assertThrows<AssertionError> { bitMap[-1, 1] }
        assertThrows<AssertionError> { bitMap[8, 1] }
        assertThrows<AssertionError> { bitMap[10, 1] }
        assertThrows<AssertionError> { bitMap[1, -1] }
        assertThrows<AssertionError> { bitMap[1, 9] }
        assertThrows<AssertionError> { bitMap[1, 12] }
    }
}