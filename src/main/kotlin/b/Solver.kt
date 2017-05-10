package b

typealias Number = List<Int>

object Solver {
    fun solve(number: Number): Number {
        return toTidy(number).dropWhile { it == 0 }
    }

    tailrec private fun toTidy(number: Number): Number {
        var prev = -1
        val tidyFracture = number.indexOfFirst { val p = it < prev; prev = it; p }
        return if (tidyFracture == -1) number
        else toTidy(number
                .take(tidyFracture - 1)
                .plus(number[tidyFracture - 1].dec())
                .plus(fill(number.size - tidyFracture, 9)))
    }

    private fun fill(length: Int, value: Int) = (1..length).map { value }
}