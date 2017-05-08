package a

import java.io.File

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        File("/home/pavel/IdeaProjects/test2/src/main/resources/a/A-large-practice.in").readLines()
        val problem = parseProblem("-+++++++++ 2")
        println(Solver.solve(problem))
    }



    fun parseProblem(str: String): Problem {
        val split = str.split(" ")
        val flipSize = split[1].toInt()
        val state = split[0].map { it == '+' }
        return Problem(State(state), flipSize)
    }
}