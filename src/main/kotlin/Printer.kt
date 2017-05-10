import java.io.File

object Printer {
    private fun writeLine(str: String, file: File) = file.appendText(str + System.lineSeparator())

    fun print(tasks: List<Pair<String, String>>, solver: (String) -> String) = tasks.forEach {
        val outFile = File(it.second)
        outFile.delete()
        File(it.first)
                .readLines().drop(1).map(solver)
                .mapIndexed { i, res -> val case = i + 1; "Case #$case: $res" }
                .forEach { writeLine(it, outFile) }
    }
}