package a

data class State(val elems: List<Boolean>) {
    fun flipped(start: Int, flipSize: Int): State? =
            if (start + flipSize <= elems.size)
                State(elems.mapIndexed { i, value ->
                    if (i >= start && i < start + flipSize) !value else value
                })
            else null

    fun solved(): Boolean = elems.reduce { a, b -> a && b }
}

data class Problem(val state: State, val flipSize: Int) {
    private fun flipFrom(start: Int): Problem? {
        val newState = state.flipped(start, flipSize)
        return if (newState == null) null
        else Problem(newState, flipSize)
    }

    fun nextFlipped() = flipFrom(state.elems.indexOf(false))

    fun solved(): Boolean = state.solved()
}