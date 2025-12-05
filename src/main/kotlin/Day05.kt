import java.io.File

class Day05 : Base<Day05.Data, Int>(5) {

    data class Data(
        val ranges: List<LongRange>,
        val values: List<Long>
    )

    override fun part1(input: Data): Int {
        return 0
    }

    override fun part2(input: Data): Int {
        return 0
    }

    override fun mapInputData(file: File): Data {
        val ranges = mutableListOf<LongRange>()
        val values = mutableListOf<Long>()
        file.readLines().forEach { line ->
            when {
                line.isEmpty() -> Unit
                line.contains("-") ->
                    line.split("-").let { ranges.add(LongRange(it[0].toLong(), it[1].toLong())) }

                else -> values.add(line.toLong())
            }
        }
        return Data(ranges, values)
    }
}

fun main() {
    Day05().submitAll()
}