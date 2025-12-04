import utils.println
import java.io.File

typealias Matrix<T> = List<List<T>>

class Day03 : Base<Matrix<Int>, Int>(3) {

    override fun part1(input: Matrix<Int>): Int {
        return input.sumOf { foundMax(it) }
    }

    private fun foundMax(input: List<Int>): Int {
        var result = 0
        for (i in 0..<input.lastIndex) {
            for (j in (i + 1)..input.lastIndex) {
                val d = input[i] * 10 + input[j]
                if (d > result) result = d
            }
        }
        return result
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