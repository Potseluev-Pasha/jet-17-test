package d.domain

import d.util.Directions
import d.util.Directions.Direction
import d.util.Point

abstract class Chessman(val point: Point) {
    val row = point.row
    val col = point.col
    abstract fun conflict(other: Chessman): Boolean
    abstract val value: Int
    abstract val directions: List<Direction>
    override fun toString() = asString() + " " + (point.row + 1) + " " + (point.col + 1)
    abstract fun asString(): String
    abstract infix fun merge(other: Chessman): Chessman

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other.javaClass != this.javaClass) return false
        return point == (other as Chessman).point
    }

    override fun hashCode(): Int {
        return point.hashCode()
    }
}

class Rook(point: Point) : Chessman(point) {
    override val directions: List<Direction> = Directions.lines()
    override val value = 1
    override fun asString() = "x"

    override fun merge(other: Chessman): Chessman = when (other) {
        is Bishop -> Queen(this.point)
        is Rook -> this
        else -> other.merge(this)
    }

    override fun conflict(other: Chessman) = when (other) {
        is Rook -> point.shareLine(other.point)
        is Queen -> point.shareLine(other.point)
        else -> false
    }
}

class Bishop(point: Point) : Chessman(point) {
    override val directions: List<Direction> = Directions.diags()
    override val value = 1
    override fun asString() = "+"

    override fun conflict(other: Chessman) = when (other) {
        is Bishop -> point.shareDiag(other.point)
        is Queen -> point.shareDiag(other.point)
        else -> false
    }

    override fun merge(other: Chessman): Chessman = when (other) {
        is Rook -> Queen(this.point)
        is Bishop -> this
        else -> other.merge(this)
    }
}

class Queen(point: Point) : Chessman(point) {
    override val directions: List<Direction> = Directions.all()
    override val value = 2
    override fun asString() = "o"

    override fun merge(other: Chessman) = when (other) {
        is Queen -> this
        else -> throw UnsupportedOperationException()
    }

    override fun conflict(other: Chessman) = when (other) {
        is Nil -> false
        is Queen -> point.shareLine(other.point) || point.shareDiag(other.point)
        is Bishop -> point.shareDiag(other.point)
        is Rook -> point.shareLine(other.point)
        else -> false
    }
}

class Nil(point: Point) : Chessman(point) {
    override val directions: List<Direction> = emptyList()
    override fun merge(other: Chessman): Chessman = other
    override val value = 0
    override fun conflict(other: Chessman) = false
    override fun asString() = "."
}
