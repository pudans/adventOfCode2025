package utils

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

@JvmInline
value class Point3D(private val value: Long) {
    constructor(x: Long, y: Long, z: Long) : this(
        x * 1_000_000_000_000 + y * 1_000_000 + z
    )
    val x: Long
        get() = value / 1_000_000_000_000
    val y: Long
        get() = value % 1_000_000_000_000 / 1_000_000
    val z: Long
        get() = value % 1_000_000

    override fun toString(): String = "Point(x=$x, y=$y, z=$z)"
}

fun Point3D.distanceTo(anotherPoint: Point3D): Double =
    sqrt((x - anotherPoint.x).toDouble().pow(2)
            + (y - anotherPoint.y).toDouble().pow(2)
            + (z - anotherPoint.z).toDouble().pow(2))