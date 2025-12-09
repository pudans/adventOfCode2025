import utils.Point
import java.io.File
import kotlin.math.abs

class Day09 : Base<List<Point<Long>>, Long>(9) {

    override fun part1(input: List<Point<Long>>): Long {
        val areas = mutableListOf<Long>()
        for (i in 0..<input.lastIndex) {
            for (j in i + 1..input.lastIndex) {
                areas.add(abs(input[i].x - input[j].x + 1) * abs(input[i].y - input[j].y + 1))
            }
        }
        return areas.max()
    }

    override fun part2(input: List<Point<Long>>): Long {
        return 0L
    }

    override fun mapInputData(file: File): List<Point<Long>> =
        file.readLines().map { str ->
            str.split(",").let { Point(it[0].toLong(), it[1].toLong()) }
        }
}

fun main() {
    Day09().submitAll()
}