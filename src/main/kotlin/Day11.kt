import java.io.File

class Day11 : Base<Map<String, List<String>>, Int>(11) {

    val memory = mutableMapOf<String, Int>()

    override fun part1(input: Map<String, List<String>>): Int {
        return walk(input, "you", emptySet())
    }

    private fun walk(input: Map<String, List<String>>, point: String, path: Set<String>): Int {
        if (memory.containsKey(point)) {
            return memory[point]!!
        }
        if (point == "out") {
            return 1
        }
        if (path.contains(point)) {
            return 0
        }
        val newSet = HashSet(path).apply { add(point) }
        val points = input[point]
        return points!!.sumOf { point ->
            val result = walk(input, point, newSet)
            memory[point] = result
            result
        }
    }

    override fun part2(input: Map<String, List<String>>): Int {
        return 0
    }

    override fun mapInputData(file: File): Map<String, List<String>> {
        val data = mutableMapOf<String, List<String>>()
        file.readLines().map { str ->
            val split = str.split(":").map { it.trim() }
            data[split[0]] = split[1].split(" ")
        }
        return data
    }
}

fun main() {
    Day11().submitAll()
}