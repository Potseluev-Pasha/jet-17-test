package d.domain

import d.util.Point
import d.util.copy
import d.util.forall
import d.util.generate

data class Grid(val chess: List<List<Chessman>>) {
    companion object Factory {
        fun withSize(n: Int): Grid = Grid((1..n).map { row -> (1..n).map { Nil(Point(row - 1, it - 1)) } })
    }

    val n = chess.size

    fun changed(chessman: Chessman): Grid? =
            if (chessman.valid()) unsafeChanged(chessman)
            else null

    private fun Chessman.valid() = needToCheck(this).forall { !it.conflict(this) }

    fun unsafeChanged(chessman: Chessman): Grid = unsafeChanged(listOf(chessman))

    fun unsafeChanged(chess: List<Chessman>): Grid =
            Grid(this.chess.copy {
                chess.forEach {
                    chessman ->
                    this[chessman.row] = this[chessman.row].copy { this[chessman.col] = chessman }
                }
            })

    private fun needToCheck(chessman: Chessman): Collection<Chessman> =
            chessman.directions.flatMap { dir ->
                generate(from = dir.next(chessman.point),
                        generator = { dir.next(it) },
                        until = { it.inField() })
            }.map { chess[it.row][it.col] }

    val value: Int = chess.flatten().sumBy { it.value }

    override fun toString(): String =
            chess.joinToString(separator = "\n", postfix = "\n", transform = {
                it.joinToString(separator = " ")
            })

    fun contains(point: Point) = point.inField()

    fun Point.inField(): Boolean = row in 0..(n - 1) && col in 0..(n - 1)
}