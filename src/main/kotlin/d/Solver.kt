package d

import d.domain.Bishop
import d.domain.Chessman
import d.domain.Grid
import d.domain.Rook
import d.util.Directions.Direction
import d.util.Point
import d.util.generate

object Solver {

    fun solve(grid: Grid): Grid {
        val gridWithRooks = place(
                points = grid.chess.flatten().map { it.point },
                grid = grid,
                chessman = { p: Point -> Rook(p) })
        val gridWithBishops = place(
                points = diag(grid).flatMap { linesFromCorner(it, grid) },
                grid = grid,
                chessman = { p: Point -> Bishop(p) })
        return gridWithBishops merge gridWithRooks
    }

    private fun diag(grid: Grid): List<Point> =
            generateSequence(Point(0, 0), { Direction.DOWN_RIGHT.next(it) }).take(grid.n).toList()

    private fun linesFromCorner(start: Point, grid: Grid): List<Point> {
        val topLine = generate(start, { Direction.RIGHT.next(it) }, { grid.contains(it) })
        val leftLine = generate(Direction.DOWN.next(start), { Direction.DOWN.next(it) }, { grid.contains(it) })
        return topLine + leftLine
    }

    tailrec private fun place(points: List<Point>, grid: Grid, chessman: (Point) -> Chessman): Grid =
            if (points.isEmpty()) grid
            else place(points.drop(1),
                    grid.changed(chessman(points.first())) ?: grid,
                    chessman)

    infix fun Grid.merge(other: Grid): Grid {
        val chess = this.chess.flatten()
                .zip(other.chess.flatten())
                .map { it.first merge it.second }
        return this.unsafeChanged(chess)
    }
}