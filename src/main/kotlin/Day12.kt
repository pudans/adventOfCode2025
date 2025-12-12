import java.io.File
import kotlin.random.Random

class Day12 : Base<Day12.Data, Int>(12) {

    data class Data(
        val shapes: List<Matrix<Char>>,
        val regions: List<Region>,
    )

    data class Region(
        val width: Int,
        val height: Int,
        val shapes: List<Int>
    )

    override fun part1(input: Data): Int {
        return input.regions.count { fit(input.shapes, it) }
    }

    private fun fit(shapes: List<Matrix<Char>>, region: Region): Boolean {
        val random = Random(System.nanoTime())
        repeat(100) {
            val rotations = shapes.map { random.nextInt(4) }
            val positions  = shapes.map {
                random.nextInt(1, region.width - 1) to (random.nextInt(1, region.height - 1))
            }
            val field = Array(region.width) { i -> IntArray(region.height) { 0} }
            shapes.forEachIndexed { index, shape ->
                val position = positions[index]
                val rotated = rotate(rotations[index], shape)

                if (rotated[0][0] == '#') field[position.first - 1][position.second - 1]
                if (rotated[1][0] == '#') field[position.first - 1][position.second]
                if (rotated[2][0] == '#') field[position.first - 1][position.second + 1]
                if (rotated[0][1] == '#') field[position.first][position.second - 1]
                if (rotated[1][1] == '#') field[position.first][position.second]
                if (rotated[2][1] == '#') field[position.first][position.second + 1]
                if (rotated[0][2] == '#') field[position.first + 1][position.second - 1]
                if (rotated[1][2] == '#') field[position.first + 1][position.second]
                if (rotated[2][2] == '#') field[position.first + 1][position.second + 1]
            }
            if (field.all { it.all { it <= 1} }) return true
        }
        return false
    }

    private fun rotate(rotation: Int, shape: Matrix<Char>): Matrix<Char> {
        when (rotation) {
            0 -> return shape
            1 -> return listOf(
                listOf(shape[2][0], shape[1][0], shape[0][0]),
                listOf(shape[2][1], shape[1][1], shape[0][1]),
                listOf(shape[2][2], shape[1][2], shape[0][2]),
            )
            2 -> return listOf(
                listOf(shape[2][2], shape[2][1], shape[2][0]),
                listOf(shape[1][2], shape[1][1], shape[1][0]),
                listOf(shape[0][2], shape[0][1], shape[0][0]),
            )
            else -> return listOf(
                listOf(shape[0][2], shape[1][2], shape[2][2]),
                listOf(shape[0][1], shape[1][1], shape[2][1]),
                listOf(shape[0][0], shape[1][0], shape[2][0]),
            )
        }
    }

    override fun part2(input: Data): Int {
        return 0
    }

    override fun mapInputData(file: File): Data {
        val shapes = mutableListOf<Matrix<Char>>()
        val regions = mutableListOf<Region>()

        var lineIndex = 0
        val lines = file.readLines()
        while (lineIndex < lines.size) {
            if (lines[lineIndex].isEmpty()) {
                lineIndex++
                continue
            }
            val split = lines[lineIndex].split(":")
            if (split[1].isEmpty()) {
                shapes.add(
                    listOf(
                        lines[lineIndex + 1].toCharArray().toList(),
                        lines[lineIndex + 2].toCharArray().toList(),
                        lines[lineIndex + 3].toCharArray().toList(),
                    )
                )
                lineIndex += 4
            } else {
                val split2 = split[0].split("x")
                regions.add(
                    Region(
                        width = split2[0].toInt(),
                        height = split2[1].toInt(),
                        shapes = split[1].trim().split(" ").map { it.toInt() }
                    )
                )
                lineIndex ++
            }
        }
        return Data(shapes, regions)
    }
}

fun main() {
    Day12().submitPart1TestInput()
}