import java.io.File

class Day06 : Base<List<Day06.Data>, Long>(6) {

    data class Data(
        val values: List<String>,
        val operation: Char
    )

    override fun part1(input: List<Data>): Long {
        return input.sumOf { data ->
            val values = data.values.map { it.filterNot { it == ' ' }.toLong() }
            when (data.operation) {
                '*' -> values.fold(1L) { acc, lng -> acc * lng }
                else -> values.sum()
            }
        }
    }

    override fun part2(input: List<Data>): Long {
        return 0L
    }

    override fun mapInputData(file: File): List<Data> {
        val lines = file.readLines()
        val operations = lines.last()
        val result = mutableListOf<Data>()
        var operationIndex = -1
        for (i in operations.indices) {
            if (operations[i] != ' ') {
                if (operationIndex >= 0) {
                    result.add(
                        Data(
                            operation = operations[operationIndex],
                            values = lines.dropLast(1).map {
                                it.substring(startIndex = operationIndex, endIndex = i - 1)
                            }
                        )
                    )
                }
                operationIndex = i
            }
        }
        val lastIndex = lines.maxOf { it.lastIndex }
        result.add(
            Data(
                operation = operations[operationIndex],
                values = lines.dropLast(1).map { line ->
                    var dd = ""
                    for (i in operationIndex..lastIndex) {
                        dd += if (i <= line.lastIndex) {
                            line[i]
                        } else {
                            ' '
                        }
                    }
                    dd
                }
            )
        )
        return result
    }
}

fun main() {
    Day06().submitAll()
}