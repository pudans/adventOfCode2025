import utils.Point
import java.io.File

class Day09 : Base<List<Point<Int>>, Long>(9) {

    override fun part1(input: List<Point<Int>>): Long {
        return 0L
    }

    override fun part2(input: List<Point<Int>>): Long {
        return 0L
    }

    override fun mapInputData(file: File): List<Point<Int>> =
        file.readLines().map { str ->
            str.split(",").let { Point(it[0].toInt(), it[1].toInt()) }
        }
}

fun main() {
    Day09().submitAll()
}