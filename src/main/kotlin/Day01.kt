import utils.Direction
import java.io.File

class Day01 : Base<List<Pair<Direction, Int>>, Int>(1) {

    private val initPosition = 50

    override fun part1(input: List<Pair<Direction, Int>>): Int {
        var position = initPosition
        var result = 0

        input.forEach { step ->
            if (position == 0) result++
            repeat(step.second) {
                when (step.first) {
                    Direction.LEFT -> position--
                    Direction.RIGHT -> position++
                    else -> Unit
                }
                if (position == -1) position = 99
                if (position == 100) position = 0
            }
        }

        return result
    }

    override fun part2(input: List<Pair<Direction, Int>>): Int {
        var position = initPosition
        var result = 0

        input.forEach { step ->
            repeat(step.second) {
                if (position == 0) result++
                when (step.first) {
                    Direction.LEFT -> position--
                    Direction.RIGHT -> position++
                    else -> Unit
                }
                if (position == -1) position = 99
                if (position == 100) position = 0
            }
        }

        return result
    }

    override fun mapInputData(file: File): List<Pair<Direction, Int>> =
        file.readLines()
            .map { str ->
                val direction = if (str.first() == 'L') Direction.LEFT else Direction.RIGHT
                return@map Pair(direction, str.drop(1).toInt())
            }
}

fun main() {
    Day01().submitAll()
}