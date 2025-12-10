import utils.println
import java.io.File

class Day10 : Base<List<Day10.Data>, Int>(10) {

    data class Data(
        val target: List<Boolean>,
        val buttons: List<List<Int>>,
        val joltage: List<Int>
    )

    override fun part1(input: List<Data>): Int {
        println(input)
        return 0
    }

    override fun part2(input: List<Data>): Int {
        return 0
    }

    override fun mapInputData(file: File): List<Data> =
        file.readLines().map { str ->
            val split = str.split(" ")
            Data(
                target = split[0]
                    .filter { it != '[' && it != ']' }
                    .map { it == '#' },
                buttons = split
                    .drop(1)
                    .dropLast(1)
                    .map { it.filter { it != '(' && it != ')' } }
                    .map { it.split(",").map { it.toInt() } },
                joltage = split.last()
                    .filter { it != '{' && it != '}' }
                    .split(",")
                    .map { it.toInt() },
            )
        }
}

fun main() {
    Day10().submitAll()
}