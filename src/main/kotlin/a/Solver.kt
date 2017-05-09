package a

object Solver {
    fun solve(problem: Problem): Int? = solve(problem, 0)

    tailrec private fun solve(problem: Problem, step: Int): Int? =
            if (problem.solved()) step
            else {
                val flipped = problem.nextFlipped()
                if (flipped == null) null
                else solve(flipped, step + 1)
            }
}