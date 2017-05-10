package c

object Solver {
    tailrec fun solve(p: Problem): List<Long> = when (p.people) {
        p.stalls -> listOf(0, 0)
        1L -> p.stalls.split()
        else -> solve(p.stalls.split().zip(p.people.split()).map { Problem(it) }.max()!!)
    }

    fun Long.split(): List<Long> {
        val x = this / 2
        return listOf(x, this - x - 1)
    }
}