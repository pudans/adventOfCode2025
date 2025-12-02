import java.io.File

class Day02 : Base<List<Pair<Long, Long>>, Long>(3) {

    override fun part1(input: List<Pair<Long, Long>>): Long {
        return 0L
    }

    override fun part2(input: List<Pair<Long, Long>>): Long {
        return 0L
    }

    override fun mapInputData(file: File): List<Pair<Long, Long>> =
        file.readLines().first().split(",")
            .map { str ->
                val split = str.split("-").map { it.toLong() }
                return@map Pair(split[0], split[1])
            }
}

fun main() {
    Day02().submitAll()
}