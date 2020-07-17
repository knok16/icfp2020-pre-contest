import java.io.File
import javax.imageio.ImageIO


fun main() {
    File("messages").listFiles()?.forEach { file ->
        println(file.name)

        val image = ImageIO.read(file)

        val bitMap = readMessage(image, figureOutTileSize(image).coerceAtLeast(1))

        val range = 2..bitMap.width * bitMap.height / 8

        val elements = split(bitMap).filter { it.width * it.height in range }

        visualize("output/${file.name}.svg", bitMap, elements)
    }
}

