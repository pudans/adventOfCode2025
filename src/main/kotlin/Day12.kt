import java.io.File

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
        return 0
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
    Day12().submitAll()
}