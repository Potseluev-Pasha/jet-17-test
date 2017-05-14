package d

import Printer
import d.domain.*
import d.util.Point
import java.io.File
import java.util.stream.Collectors.toList

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val initialGrids = File("./src/main/resources/d/D-large-practice.in").readGrids()
        val solutions = initialGrids.parallelStream().map { Solver.solve(it) }.collect(toList())
        File("./src/main/results/d/D-large-practice.out").printResults(initialGrids, solutions)
    }
}

private fun File.readGrids(): List<Grid> {
    val lines = this.readLines().iterator()
    return (1..lines.next().toInt()).map {
        val nm = lines.next().split(" ").map { it.toInt() }
        readGrid(lines, nm[0], nm[1])
    }
}

private fun readGrid(lines: Iterator<String>, n: Int, m: Int): Grid {
    val grid = Grid.withSize(n)
    val chess = (1..m).map { lines.next().toChessman() }
    return grid.unsafeChanged(chess)
}

private fun String.toChessman(): Chessman {
    val s = this.split(" ")
    return Chess.of(s[0], Point(s[1].toInt() - 1, s[2].toInt() - 1))
}

private fun File.printResults(initialGrids: List<Grid>, solutions: List<Grid>) {
    this.delete()
    initialGrids.zip(solutions).map {
        val diff = it.first.diff(it.second)
        "" + it.second.value + " " + diff.size +
                diff.fold("") { acc, chessman -> acc + System.lineSeparator() + chessman }
    }.mapIndexed { index, str -> Printer.case(index + 1, str) }
            .forEach { Printer.writeLine(it, this) }
}

private fun Grid.diff(other: Grid): List<Chessman> =
        chess.flatten()
                .zip(other.chess.flatten())
                .filterNot { it.first == it.second }
                .map { it.second }

object Chess {
    fun of(str: String, point: Point): Chessman = when (str) {
        "+" -> Bishop(point)
        "x" -> Rook(point)
        "o" -> Queen(point)
        "." -> Nil(point)
        else -> throw IllegalArgumentException()
    }
}