package d.util

data class Point(val row: Int, val col: Int) {
    fun shareRow(other: Point) = row == other.row

    fun shareCol(other: Point) = col == other.col

    fun shareLine(other: Point) = shareCol(other) || shareRow(other)

    fun shareDiag(other: Point) = col + row == other.col + other.row
            || (col - row) == other.col - other.row
}