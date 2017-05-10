package c

data class Problem(val stalls: Long, val people: Long) : Comparable<Problem> {
    override fun compareTo(other: Problem): Int = if (people * other.stalls > other.people * stalls) 1 else -1

    constructor(stallsPeople: Pair<Long, Long>) : this(stallsPeople.first, stallsPeople.second)
}

object Solver {
    tailrec fun solve(p: Problem): List<Long> =
            when (p.people) {
                p.stalls -> listOf(0, 0)
                1L -> split(p.stalls)
                else -> solve(split(p.stalls).zip(split(p.people)).map { Problem(it) }.max()!!)
            }

    private fun split(n: Long): List<Long> {
        val x = n / 2
        return listOf(x, n - x - 1)
    }
}