import utils.Point3D
import utils.println
import java.io.File

class Day08 : Base<List<Point3D>, Long>(8) {

    override fun part1(input: List<Point3D>): Long {
        println(input)
        return 0L
    }

    override fun part2(input: List<Point3D>): Long {
        return 0L
    }

    override fun mapInputData(file: File): List<Point3D> =
        file.readLines().map { str ->
            str.split(",").map { it.toLong() }.let { Point3D(it[0], it[1], it[2]) }
        }
}

fun main() {
    Day08().submitAll()
}