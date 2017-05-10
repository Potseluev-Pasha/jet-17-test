package a

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        fun parseProblem(str: String): Problem {
            val split = str.split(" ")
            val flipSize = split[1].toInt()
            val state = split[0].map { it == '+' }
            return Problem(state, flipSize)
        }

        val paths = listOf("./src/main/resources/a/A-large-practice.in"
                to "./src/main/results/a/A-large-practice.out")

        Printer.print(tasks = paths, solver = { (Solver.solve(parseProblem(it)) ?: "IMPOSSIBLE").toString() })
    }
}