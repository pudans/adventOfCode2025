import java.io.File

typealias Matrix<T> = List<List<T>>

class Day03 : Base<Matrix<Int>, Int>(3) {

    override fun part1(input: Matrix<Int>): Int {
        return 0
    }

    override fun part2(input: Matrix<Int>): Int {
        return 0
    }

    override fun mapInputData(file: File): Matrix<Int> =
        file.readLines()
            .map { str -> str.map { it.toString().toInt() } }
}

fun main() {
    Day03().submitAll()
}