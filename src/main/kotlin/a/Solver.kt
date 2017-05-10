package a

object Solver {
    fun solve(problem: Problem, step: Int = 0): Int? =
            if (problem.solved()) step
            else problem.nextFlipped()?.let { solve(it, step + 1) }

    fun Problem.nextFlipped() = flippedFrom(state.indexOf(false))
}