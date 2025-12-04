import utils.println
import java.io.File

typealias Matrix<T> = List<List<T>>

class Day03 : Base<Matrix<Int>, Int>(3) {

    override fun part1(input: Matrix<Int>): Int {
        return input.take(1).sumOf {
            val r = foundMax2(it, -1, 2, sum = 0, lvl = 0)
            println("result $r")
            r
        }
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

    private fun foundMax2(input: List<Int>, i: Int, len: Int, sum: Int, lvl: Int): Int {
        println("foundMax2 $input $i $len $sum")
        if (lvl == len) return sum
        return buildList {
            for (j in (i + 1)..(input.size - (len - i))) {
                val newSum = sum * 10 + input[j]
                add(foundMax2(input, j, len, newSum, lvl + 1))
            }
        }.max()
    }

    override fun part2(input: Matrix<Int>): Int {
        return 0
    }

    override fun mapInputData(file: File): Matrix<Int> =
        file.readLines()
            .map { str -> str.map { it.toString().toInt() } }
}

fun main() {
    Day03().submitPart1TestInput()
}