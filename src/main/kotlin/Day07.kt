import utils.PointX
import utils.println
import java.io.File
import java.util.LinkedList
import java.util.Queue
import java.util.TreeMap

class Day07 : Base<Day07.Data, Long>(7) {

    data class Data(
        val start: PointX,
        val splitter: List<PointX>,
        val width: Int,
        val height: Int
    )

    data class TreeNode(
        val point: PointX,
        val nodes: MutableList<TreeNode>
    )

    override fun part1(input: Data): Long {
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
        return splits.size.toLong()
    }

    val pass: Queue<PointX> = LinkedList()

    override fun part2(input: Data): Long {
        var result = 0L
        pass.add(input.start)
        while (pass.isNotEmpty()) {
            val pointToWalk = pass.remove()
            if (walk(input, pointToWalk)) {
                result++
            }
        }
        return result
    }


    private fun walk(input: Data, point: PointX): Boolean {
        if (point.x == input.height - 1 && point.y >= 0 && point.y < input.width) {
            return true
        }
        if (input.splitter.contains(point)) {
            pass.add(PointX(point.x + 1, point.y + 1))
            pass.add(PointX(point.x + 1, point.y - 1))
        } else {
            pass.add(PointX(point.x + 1, point.y))
        }
        return false
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
    Day07().submitPart2Input()
}