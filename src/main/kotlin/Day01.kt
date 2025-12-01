import utils.Direction
import java.io.File

class Day01 : Base<List<Pair<Direction, Int>>, Int>(1) {

    override fun part1(input: List<Pair<Direction, Int>>): Int {
        return 0
    }

    override fun part2(input: List<Pair<Direction, Int>>): Int {
        return 0
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