import java.io.File

class Day02 : Base<List<Pair<Long, Long>>, Long>(2) {

    override fun part1(input: List<Pair<Long, Long>>): Long {
        var result = 0L
        input.forEach {
            for (i in it.first..it.second) {
                val str = i.toString()
                if (str.length % 2 == 0) {
                    val str1 = str.take(str.length / 2)
                    val str2 = str.drop(str.length / 2)
                    if (str1 == str2) {
                        result += i
                    }
                }
            }
        }
        return result
    }

    override fun part2(input: List<Pair<Long, Long>>): Long {
        var result = 0L
        input.forEach {
            for (i in it.first..it.second) {
                if (checkInvalid(i.toString())) {
                    result += i
                }

            }
        }
        return result
    }

    private fun checkInvalid(input: String): Boolean {
        if (input.all { it == input.first() }) return true

//        if (input.length >= 4 && input.length % 2 == 0) {
        if (input.length >= 4) {
            for (j in 2..input.length / 2) {
                val c = input.take(j)
                var d = ""
                repeat(input.length / j) { d += c }
                if (d == input) return true
            }
        }
        return false
    }

    override fun mapInputData(file: File): List<Pair<Long, Long>> =
        file.readLines().first().split(",")
            .map { str ->
                val split = str.split("-").map { it.toLong() }
                return@map Pair(split[0], split[1])
            }
}

fun main() {
    Day02().submitAll()
}