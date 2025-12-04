import java.io.File

class Day04 : Base<Matrix<Char>, Int>(4) {

    override fun part1(input: Matrix<Char>): Int {
        var result = 0
        for (i in input.indices) {
            for (j in input.first().indices) {
                if (input[i][j] == '@') {
                    var d = 0
                    if (input.isRoll(i + 1, j)) d++
                    if (input.isRoll(i - 1, j)) d++
                    if (input.isRoll(i + 1, j + 1)) d++
                    if (input.isRoll(i - 1, j - 1)) d++
                    if (input.isRoll(i + 1, j - 1)) d++
                    if (input.isRoll(i - 1, j + 1)) d++
                    if (input.isRoll(i, j + 1)) d++
                    if (input.isRoll(i, j - 1)) d++

                    if (d < 4) result++
                }
            }
        }
        return result
    }

    private fun Matrix<Char>.isRoll(i: Int, j: Int): Boolean =
        i >= 0 && j >= 0 && i < this.size && j < this.first().size && this[i][j] == '@'

    override fun part2(input: Matrix<Char>): Int {
        return 0
    }

    override fun mapInputData(file: File): Matrix<Char> =
        file.readLines()
            .map { str -> str.toCharArray().toList() }
}

fun main() {
    Day04().submitAll()
}