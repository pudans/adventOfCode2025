import java.io.File

class Day11 : Base<Map<String, List<String>>, Int>(11) {

    override fun part1(input: Map<String, List<String>>): Int {
        return 0
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