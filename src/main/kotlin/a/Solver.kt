package a

object Solver {
    fun solve(problem: Problem): Int = bfs(sequenceOf(problem to 0), emptySet())

    fun newStates(problem: Problem, counter: Int, visited: Set<State>): List<Pair<Problem, Int>> =
            problem.neighbors()
                    .filterNot { visited.contains(it.state) }
                    .map { it to (counter + 1) }

    fun bfs(queue: Sequence<Pair<Problem, Int>>, visited: Set<State>): Int =
            if (!queue.iterator().hasNext()) -1
            else {
                val (problem, counter) = queue.first()
                if (problem.solved()) counter
                else bfs(queue.drop(1) lazyPlus { newStates(problem, counter, visited).asSequence() },
                        visited + problem.state)
            }

    infix fun <T> Sequence<T>.lazyPlus(otherGenerator: () -> Sequence<T>) =
            object : Sequence<T> {
                private val thisIterator: Iterator<T> by lazy { this@lazyPlus.iterator() }
                private val otherIterator: Iterator<T> by lazy { otherGenerator().iterator() }

                override fun iterator() = object : Iterator<T> {
                    override fun next(): T =
                            if (thisIterator.hasNext())
                                thisIterator.next()
                            else
                                otherIterator.next()

                    override fun hasNext(): Boolean =
                            thisIterator.hasNext() || otherIterator.hasNext()
                }
            }
}
