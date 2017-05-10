package b

typealias Number = List<Int>

object Solver {
    fun solve(number: Number): Number = toTidy(number).dropWhile { it == 0 }

    private fun toTidy(number: Number): Number = number.tidyFracture()?.let {
        toTidy(number.take(it - 1)
                + number[it - 1].dec()
                + fill(number.size - it, 9))
    } ?: number

    fun Number.tidyFracture(): Int? {
        var prev = -1
        return indexOfFirst { val p = it < prev; prev = it; p }.takeIf { it >= 0 }
    }

    private fun fill(length: Int, value: Int) = (1..length).map { value }
}