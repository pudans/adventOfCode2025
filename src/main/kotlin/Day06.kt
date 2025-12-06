import java.io.File

class Day06 : Base<List<Day06.Data>, Long>(6) {

    data class Data(
        val values: List<Long>,
        val operation: Char
    )

    override fun part1(input: List<Data>): Long {
        return 0L
    }

    override fun part2(input: List<Data>): Long {
        return 0L
    }

    override fun mapInputData(file: File): List<Data> {
        val lines = file.readLines().map { it.split(" ").filter { it.isNotEmpty() } }
        return lines[4].mapIndexed { index, string ->
            Data(
                operation = string.first(),
                values = listOf(
                    lines[0][index].toLong(),
                    lines[1][index].toLong(),
                    lines[2][index].toLong(),
                    lines[3][index].toLong(),
                )
            )
        }
    }
}

fun main() {
    Day06().submitPart2Input()
}