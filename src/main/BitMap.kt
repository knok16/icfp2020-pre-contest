import java.awt.Color
import java.awt.image.BufferedImage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min


class BitMap(
    val width: Int,
    val height: Int,
    private val bitMap: Array<BooleanArray>,
    val columnOffset: Int,
    val rowOffset: Int
) {
    operator fun get(column: Int, row: Int): Boolean {
        assert(0 <= column)
        assert(column < width)
        assert(0 <= row)
        assert(row < height)

        return bitMap[rowOffset + row][columnOffset + column]
    }

    fun subRegion(columnOffset: Int, rowOffset: Int, width: Int, height: Int): BitMap {
        assert(0 <= columnOffset)
        assert(columnOffset + width <= this.width)
        assert(0 <= rowOffset)
        assert(rowOffset + height <= this.height)

        return BitMap(width, height, bitMap, this.columnOffset + columnOffset, this.rowOffset + rowOffset)
    }
}

fun bitMapFrom(array: Array<BooleanArray>): BitMap {
    val height = array.size

    assert(height > 0)

    val width = array[0].size

    assert(array.all { it.size == width })

    return BitMap(width, height, array, 0, 0)
}

fun figureOutTileSize(image: BufferedImage, emptyColor: Int = Color.BLACK.rgb): Int =
    (0 until image.height).firstOrNull { row ->
        (0 until image.width).any { column -> image.getRGB(column, row) == emptyColor }
    } ?: image.height

fun readMessage(image: BufferedImage, tileSize: Int, emptyColor: Int = Color.BLACK.rgb): BitMap {
    assert(image.width % tileSize == 0)
    assert(image.height % tileSize == 0)

    val width = image.width / tileSize
    val height = image.height / tileSize

    val bitMap = Array(height) { row ->
        BooleanArray(width) { column ->
            image.getRGB(column * tileSize, row * tileSize) != emptyColor
        }
    }

    return BitMap(width, height, bitMap, 0, 0)
}

fun writeMessage(bitMap: BitMap, tileSize: Int = 2, trueColor: Color = Color.WHITE): BufferedImage {
    val result = BufferedImage(bitMap.width * tileSize, bitMap.height * tileSize, BufferedImage.TYPE_INT_RGB)

    val graphics = result.graphics
    graphics.color = trueColor

    for (row in 0 until bitMap.height)
        for (column in 0 until bitMap.width)
            if (bitMap[column, row])
                graphics.fillRect(column * tileSize, row * tileSize, tileSize, tileSize)

    return result
}

fun split(bitMap: BitMap): List<BitMap> {
    val d = arrayOf(-1, 0, 1)

    data class Rectangle(
        val x: Int,
        val y: Int,
        val width: Int,
        val height: Int
    )

    val checked = Array(bitMap.height) { BooleanArray(bitMap.width) }

    val result = ArrayList<Rectangle>()

    val q = LinkedList<Pair<Int, Int>>()

    val rows = 0 until bitMap.height
    val columns = 0 until bitMap.width

    for (rowOffset in rows) {
        for (columnOffset in columns)
            if (!checked[rowOffset][columnOffset] && bitMap[columnOffset, rowOffset]) {
                q.offer(columnOffset to rowOffset)
                checked[rowOffset][columnOffset] = true
                var left = columnOffset
                var right = columnOffset
                var top = rowOffset
                var bottom = rowOffset
                while (!q.isEmpty()) {
                    val (x, y) = q.remove()

                    left = min(left, x)
                    right = max(right, x)
                    top = min(top, y)
                    bottom = max(bottom, y)

                    for (dx in d)
                        for (dy in d) {
                            val nx = x + dx
                            val ny = y + dy
                            if (nx in columns && ny in rows && !checked[ny][nx] && bitMap[nx, ny]) {
                                checked[ny][nx] = true
                                q.offer(nx to ny)
                            }
                        }
                }
                result.add(Rectangle(left, top, right - left + 1, bottom - top + 1))
            }
    }

    return result.distinct().map { bitMap.subRegion(it.x, it.y, it.width, it.height) }
}

