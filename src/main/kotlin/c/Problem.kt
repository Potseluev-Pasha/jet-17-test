package c

data class Problem(val stalls: Long, val people: Long) : Comparable<Problem> {
    override fun compareTo(other: Problem): Int = if (people * other.stalls > other.people * stalls) 1 else -1

    constructor(stallsPeople: Pair<Long, Long>) : this(stallsPeople.first, stallsPeople.second)
}