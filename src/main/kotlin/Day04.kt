import java.io.File

class Day04 : Base<Matrix<Char>, Int>(4) {

    override fun part1(input: Matrix<Char>): Int {
        return 0
    }

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