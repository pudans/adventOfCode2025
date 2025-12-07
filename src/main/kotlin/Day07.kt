import utils.PointX
import java.io.File

class Day07 : Base<Day07.Data, Int>(7) {

    data class Data(
        val start: PointX,
        val splitter: List<PointX>,
        val width: Int,
        val height: Int
    )

    override fun part1(input: Data): Int {
        val splits = mutableSetOf<PointX>()
        val beams = mutableSetOf<PointX>()
        beams.add(input.start)

        for (i in 1..<input.height) {
            for (j in 0..<input.width) {
                val isSplitter = input.splitter.contains(PointX(i, j))
                val isBeam = beams.contains(PointX(i - 1, j))
                if (isSplitter && isBeam) {
                    beams.add(PointX(i, j - 1))
                    beams.add(PointX(i, j + 1))
                    splits.add(PointX(i, j))
                } else if (isBeam) {
                    beams.add(PointX(i, j))
                }
            }
        }
        return splits.size
    }

    override fun part2(input: Data): Int {
        return 0
    }

    override fun mapInputData(file: File): Data {
        val lines = file.readLines()
        val startY = lines[0].indexOf('S')
        val splitter = mutableListOf<PointX>()
        for (j in lines.indices) {
            for (i in lines[j].indices) {
                if (lines[j][i] == '^') {
                    splitter.add(PointX(j, i))
                }
            }
        }
        return Data(
            start = PointX(0, startY),
            width = lines[0].length,
            height = lines.size,
            splitter = splitter
        )
    }
}

fun main() {
    Day07().submitAll()
}