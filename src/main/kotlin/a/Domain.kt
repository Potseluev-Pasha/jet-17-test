package a

data class State(val elems: List<Boolean>) {
    fun flipped(start: Int, flipSize: Int): State {
        require(start + flipSize <= elems.size)
        return State(elems.mapIndexed { i, value ->
            if (i >= start && i < start + flipSize) !value else value
        })
    }

    fun solved(): Boolean = elems.reduce { a, b -> a && b }

    override fun toString(): String {
        return elems.fold("", { str, v -> str + if (v) " + " else " - " })
    }

    fun sameStates(): Set<State> = setOf(this, State(elems.asReversed()))
}

data class Problem(val state: State, val flipSize: Int) {
    private fun flipFrom(start: Int): Problem {
        return Problem(state.flipped(start, flipSize), flipSize)
    }

    fun solved(): Boolean = state.solved()

    private fun estimation() = state.elems.filterNot { it }.size

    private fun neighbors(): Set<Problem> = (0..(state.elems.size - flipSize)).map { flipFrom(it) }.toSet()

    fun perspectiveNeighbors() = neighbors().filterNot { it.estimation() - this.estimation() == flipSize }
}