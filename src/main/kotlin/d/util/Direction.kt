package d.util

import d.util.Directions.Direction.*

object Directions {

    fun all(): List<Direction> = Direction.values().toList()

    fun diags(): List<Direction> = listOf(DOWN_LEFT, UP_LEFT, DOWN_RIGHT, UP_RIGHT)

    fun lines(): List<Direction> = listOf(LEFT, RIGHT, DOWN, UP)

    enum class Direction {
        UP_RIGHT {
            override fun next(point: Point) = point.copy(point.row - 1, point.col + 1)
        },
        UP {
            override fun next(point: Point) = point.copy(point.row - 1, point.col)
        },
        DOWN {
            override fun next(point: Point) = point.copy(point.row + 1, point.col)
        },
        LEFT {
            override fun next(point: Point) = point.copy(point.row, point.col - 1)
        },
        RIGHT {
            override fun next(point: Point) = point.copy(point.row, point.col + 1)
        },
        DOWN_LEFT {
            override fun next(point: Point) = point.copy(point.row + 1, point.col - 1)
        },
        UP_LEFT {
            override fun next(point: Point) = point.copy(point.row - 1, point.col - 1)
        },
        DOWN_RIGHT {
            override fun next(point: Point) = point.copy(point.row + 1, point.col + 1)
        };

        abstract fun next(point: Point): Point
    }
}