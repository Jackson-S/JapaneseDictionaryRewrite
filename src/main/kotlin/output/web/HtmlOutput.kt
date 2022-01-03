package output.web

import output.common.datatypes.ForeignEntry
import output.common.datatypes.JapaneseEntry
import java.io.File
import java.io.FileWriter

class HtmlOutput(
    val outputPath: String
) {

    fun render(entry: JapaneseEntry) {
        val page = JapanesePage(entry).page
        println(page)
        writer("${entry.title}.html", page)
    }

    fun render(entry: ForeignEntry) {
    }

    fun writer(filename: String, output: String) {
        val outputWriter = FileWriter("$outputPath/$filename")
        outputWriter.write(output)
        outputWriter.close()
    }
}