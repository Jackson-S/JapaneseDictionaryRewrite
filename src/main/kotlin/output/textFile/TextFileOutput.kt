package output.textFile

import output.common.Dictionary
import output.common.datatypes.JapaneseEntry
import java.io.File

class TextFileOutput(
    dictionary: Dictionary
) {
    init {
        val outputFile = File("/Users/jackson/Desktop/output.txt")

        dictionary.entries.forEach {
            val entry = it as JapaneseEntry
            val sentences = entry.sentences?.joinToString { it.english }
            outputFile.appendText("${entry.title} $sentences\n")
        }
    }
}