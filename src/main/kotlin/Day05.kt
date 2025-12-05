import java.io.File
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min

class Day05 : Base<Day05.Data, Long>(5) {

    data class Data(
        val ranges: List<LongRange>,
        val values: List<Long>
    )

    override fun part1(input: Data): Long =
        input.values.count { value ->
            input.ranges.any { range -> range.contains(value) }
        }.toLong()

    override fun part2(input: Data): Long {
        var loop = true
        var input = input.ranges.sortedBy { it.first }.toMutableList()
        while (loop) {
            val result = unite(input)
            loop = result
//            input = result.toMutableList()
        }
        println(input.toString())
        return input.sumOf { it.last - it.first }
    }

    private fun unite(input: MutableList<LongRange>): Boolean {
        println("asdasd $input")
        for (i in 0..<input.lastIndex) {
            for (j in (i+1)..input.lastIndex) {
                if (input[i].last in input[j] || input[i].first in input[j] || input[i].first == input[j].first || input[i].last == input[j].last) {
                    println("asdasd $i $j")
                    input.add(LongRange(min(input[i].first, input[j].first), max(input[i].last, input[j].last)))
                    input.removeAt(i)
                    input.removeAt(j)
                    return true
                }
            }
        }
        return false
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
    Day05().submitPart2Input()
}