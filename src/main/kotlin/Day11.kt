import utils.println
import java.io.File
import kotlin.collections.forEach

class Day11 : Base<Map<String, List<String>>, Int>(11) {

    val memory = mutableMapOf<String, Int>()

    override fun part1(input: Map<String, List<String>>): Int {
        memory.clear()
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

    val memory2 = mutableMapOf<String, List<Pair<Boolean, Boolean>>>()

    override fun part2(input: Map<String, List<String>>): Int {
        memory2.clear()
        return walk2(input, "svr", emptySet())?.count { it.first && it.second } ?: 0
    }

    private fun walk2(input: Map<String, List<String>>, point: String, path: Set<String>): List<Pair<Boolean, Boolean>>? {
//        println("$point $path")
        if (memory2.containsKey(point)) {
            val mem = memory2[point]!!
            mem.forEach {
                if (it.first && it.second) {
                    return listOf(true to true)
                } else if (it.first && path.contains("fft")) {
                    return listOf(true to true)
                } else if (it.second && path.contains("dac")) {
                    return listOf(true to true)
                }
            }
        }
        if (point == "out") {
            if (path.contains("fft") && path.contains("dac")) {
                return listOf(true to true)
            } else if (path.contains("fft")) {
                return listOf(true to false)
            } else if (path.contains("dac")) {
                return listOf(false to true)
            } else {
                return null
            }
        }
        if (path.contains(point)) {
            return null
        }
        val newSet = HashSet(path).apply { add(point) }
        val points = input[point]

        val result = mutableListOf<Pair<Boolean, Boolean>>()
        points!!.forEach { point ->
            val result1 = walk2(input, point, newSet)
            if (result1 != null) {
                memory2[point] = result1
                result.addAll(result1)
            }
        }
        return result.takeIf { it.isNotEmpty() }
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
    Day11().submitPart2Input()
}