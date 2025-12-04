import utils.println
import java.io.File

typealias Matrix<T> = List<List<T>>

class Day03 : Base<Matrix<Long>, Long>(3) {

    override fun part1(input: Matrix<Long>): Long {
        return input.take(1).sumOf {
            val r = foundMax2(it, -1, 2, sum = 0, lvl = 0)
            println("result $r")
            r
        }
    }

    private fun foundMax(input: List<Long>): Long {
        var result = 0L
        for (i1 in 0..(input.lastIndex - 11)) {
            for (i2 in (i1 + 1)..input.lastIndex - 10) {
                for (i3 in (i2 + 1)..input.lastIndex - 9) {
                    for (i4 in (i3 + 1)..input.lastIndex - 8) {
                        for (i5 in (i4 + 1)..input.lastIndex - 7) {
                            for (i6 in (i5 + 1)..input.lastIndex - 6) {
                                for (i7 in (i6 + 1)..input.lastIndex - 5) {
                                    for (i8 in (i7 + 1)..input.lastIndex - 4) {
                                        for (i9 in (i8 + 1)..input.lastIndex - 3) {
                                            for (i10 in (i9 + 1)..input.lastIndex - 2) {
                                                for (i11 in (i10 + 1)..input.lastIndex - 1) {
                                                    for (i12 in (i11 + 1)..input.lastIndex) {
                                                        val d = input[i1] * 1_000_000_000_00 +
                                                                input[i2] * 100_000_000_00 +
                                                                input[i3] * 10_000_000_00 +
                                                                input[i4] * 1_000_000_00 +
                                                                input[i5] * 100_000_00 +
                                                                input[i6] * 10_000_00 +
                                                                input[i7] * 1_000_00 +
                                                                input[i8] * 100_00 +
                                                                input[i9] * 10_00 +
                                                                input[i10] * 100 +
                                                                input[i11] * 10 +
                                                                input[i12]

                                                        if (d > result) result = d
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    private fun foundMax2(input: List<Long>, i: Int, len: Int, sum: Long, lvl: Int): Long {
        println("foundMax2 $input $i $len $sum")
        if (lvl == len) return sum
        return buildList {
            for (j in (i + 1)..(input.size - (len - i))) {
                val newSum = sum * 10 + input[j]
                add(foundMax2(input, j, len, newSum, lvl + 1))
            }
        }.max()
    }

    override fun part2(input: Matrix<Long>): Long {
        return input.sumOf {
            val r = foundMax(it)
            println("result $r")
            r
        }
    }

    override fun mapInputData(file: File): Matrix<Long> =
        file.readLines()
            .map { str -> str.map { it.toString().toLong() } }
}

fun main() {
    Day03().submitPart2Input()
}