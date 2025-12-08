import utils.Point3D
import utils.distanceTo
import utils.println
import java.io.File
import java.util.LinkedList

class Day08 : Base<List<Point3D>, Long>(8) {

    override fun part1(input: List<Point3D>): Long {

        val connections = mutableListOf<MutableList<Point3D>>()

        val sortedDistances = buildList {
            for (i in 0..<input.lastIndex) {
                for (j in i + 1..input.lastIndex) {
                    add(Triple(input[i].distanceTo(input[j]), input[i], input[j]))
                }
            }
        }.sortedBy { it.first }

        var h = 0
        var g = 0
        while(g <= 999) {
            val dd = sortedDistances[h]
            val f1 = connections.indexOfFirst { it.contains(dd.second) }
            val f2 = connections.indexOfFirst { it.contains(dd.third) }
            if (f1 >= 0 && f2 >= 0 && f1 == f2) {
                // Nothing
            } else if (f1 >= 0 && f2 >= 0 && f1 != f2) {
                connections[f1].addAll(connections[f2])
                connections.removeAt(f2)
            } else if (f1 >= 0) {
                connections[f1].add(dd.third)
            } else if (f2 >= 0) {
                connections[f2].add(dd.second)
            } else {
                connections.add(mutableListOf(dd.second, dd.third))
            }
            g++
            h++
        }

        return connections.sortedBy { it.size }.takeLast(3).let {
            it.fold(1) { acc, ds -> ds.size * acc }
        }
    }

    override fun part2(input: List<Point3D>): Long {

        val connections = mutableListOf<MutableList<Point3D>>()

        val sortedDistances = buildList {
            for (i in 0..<input.lastIndex) {
                for (j in i + 1..input.lastIndex) {
                    add(Triple(input[i].distanceTo(input[j]), input[i], input[j]))
                }
            }
        }.sortedBy { it.first }

        val linked = LinkedList<Point3D>()

        var h = 0
        while(h < sortedDistances.size) {
            val dd = sortedDistances[h]
            val f1 = connections.indexOfFirst { it.contains(dd.second) }
            val f2 = connections.indexOfFirst { it.contains(dd.third) }
            if (f1 >= 0 && f2 >= 0 && f1 == f2) {
                // Nothing
            } else if (f1 >= 0 && f2 >= 0 && f1 != f2) {
                linked.addLast(connections[f1].last())

                connections[f1].addAll(connections[f2])
                linked.addLast(connections[f1].last())

                connections.removeAt(f2)
            } else if (f1 >= 0) {
                linked.addLast(connections[f1].last())
                connections[f1].add(dd.third)
                linked.addLast(dd.third)
            } else if (f2 >= 0) {
                linked.addLast(connections[f2].last())

                connections[f2].add(dd.second)
                linked.addLast(dd.second)

            } else {
                connections.add(mutableListOf(dd.second, dd.third))
            }
            h++
        }

        println(linked.takeLast(2))

        return 0L
    }

    override fun mapInputData(file: File): List<Point3D> =
        file.readLines().map { str ->
            str.split(",").map { it.toLong() }.let { Point3D(it[0], it[1], it[2]) }
        }
}

fun main() {
    Day08().submitPart2TestInput()
}