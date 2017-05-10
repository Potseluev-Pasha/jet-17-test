package a

data class Problem(val state: List<Boolean>, val flipSize: Int) {
    fun solved(): Boolean = state.reduce { a, b -> a && b }

    fun flippedFrom(start: Int): Problem? =
            if (start + flipSize > state.size) null
            else copy(state.flipped(start))

    fun List<Boolean>.flipped(start: Int) =
            this.mapIndexed { i, value -> if (i >= start && i < start + flipSize) !value else value }
}