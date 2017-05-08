package a

object Solver {
    fun solve(problem: Problem): Int? = bfs(listOf(problem to 0), emptySet())

    private fun newStates(problem: Problem, counter: Int, visited: Set<State>): List<Pair<Problem, Int>> =
            problem.perspectiveNeighbors()
                    .filterNot { it.state.sameStates().find { visited.contains(it) } != null }
                    .map { it to (counter + 1) }

    tailrec private fun bfs(queue: List<Pair<Problem, Int>>, visited: Set<State>): Int? =
            if (queue.isEmpty()) null
            else {
                val (problem, counter) = queue.first()
                if (problem.solved()) counter
                else bfs(queue.drop(1) + newStates(problem, counter, visited), visited + problem.state)
            }
}