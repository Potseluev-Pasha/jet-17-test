package b

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        fun parseProblem(str: String): Number = str.toList().map { it - '0' }

        val paths = listOf("./src/main/resources/b/B-large-practice.in"
                to "./src/main/results/b/B-large-practice.out")

        Printer.print(tasks = paths, solver = { Solver.solve(parseProblem(it)).joinToString(separator = "") })
    }
}
