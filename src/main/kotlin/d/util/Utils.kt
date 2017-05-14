package d.util

fun <T> generate(from: T, generator: (T) -> T, until: (T) -> Boolean): List<T> {
    fun loop(cur: T): List<T> = if (until(cur)) loop(generator(cur)) + cur else emptyList()
    return loop(from).asReversed()
}

fun <T> List<T>.copy(mutateBlock: MutableList<T>.() -> Unit): List<T>
        = toMutableList().apply(mutateBlock)

fun <T> Iterable<T>.forall(p: (T) -> Boolean) = find { !p(it) } == null