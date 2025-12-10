import utils.PointX
import utils.println
import java.io.File
import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

class Day10 : Base<List<Day10.Data>, Int>(10) {

    data class Data(
        val target: List<Boolean>,
        val buttons: List<List<Int>>,
        val joltage: List<Int>
    )

    override fun part1(input: List<Data>): Int = input.sumOf { walk(it) }

    val pass: Queue<Pair<Int, List<Boolean>>> = LinkedList()

    private fun walk(data: Data): Int {
        var result = 0
        pass.add(Pair(0, data.target.map { false }))
        while (pass.isNotEmpty()) {
            val step = pass.remove()
            if (pass.isNotEmpty() && step.second.all { false }) {
                continue
            }
            if (step.first >= 10) {
                continue
            }
            if (step.second == data.target) {
                result = if (result == 0) step.first else min(result, step.first)
                continue
            }
            if (result != 0 && step.first > result) {
                continue
            }
//            println(step)

            data.buttons.forEach { button ->
                val new = step.second.mapIndexed { index, bool -> if (button.contains(index)) !bool else bool }
                pass.add(step.first + 1 to new)
            }
        }
        pass.clear()
        return result
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