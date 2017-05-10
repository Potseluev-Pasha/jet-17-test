package c

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        fun parseProblem(str: String) = str.split(" ").map { it.toLong() }

        val paths = listOf("./src/main/resources/c/C-large-practice.in"
                to "./src/main/results/c/C-large-practice.out")

        Printer.print(tasks = paths, solver = {
            val p = parseProblem(it)
            Solver.solve(Problem(p[0], p[1])).joinToString(separator = " ")
        })
    }
}


