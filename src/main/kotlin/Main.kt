
import jmdict.JMDict
import kanjidic.KanjiDic2
import dataabstraction.FileIOInterface
import dataabstraction.NullIOInterface
import output.dictionary.KanjiOutput
import output.common.OutputWriter
import output.dictionary.DictionaryOutput
import sentences.TatoebaSentences
import kotlin.system.exitProcess

fun main() {
    checkConfiguration()

    println("Preparing to create ${Configuration.DICTIONARY_DISPLAY_NAME}")
    println("Output languages:\n\t${Configuration.LANGUAGE.joinToString("\n\t")}")
    println("Output directory: \"${Configuration.OUTPUT_DIRECTORY}\"")

    val sentences = loadSentences()
    val jmDict = loadJmdict()
    val kanjiDic2 = loadKanjiDic()

    if (jmDict != null) {
        println("Generating dictionary app output")
        val dictionaryOutput = DictionaryOutput(jmDict, sentences, Configuration.LANGUAGE)

        println("Writing dictionary app output to disk")
        OutputWriter().writeAll(Configuration.OUTPUT_DIRECTORY + "/dictionary", dictionaryOutput)
    }

    if (kanjiDic2 != null) {
        println("Generating kanji dictionary app output")
        val kanjiOutput = KanjiOutput(kanjiDic2, Configuration.LANGUAGE)

        println("Writing kanji dictionary app output to disk")
        OutputWriter().writeAll(Configuration.OUTPUT_DIRECTORY + "/kanji", kanjiOutput)
    }
}

fun checkConfiguration() {
    var prematureExit = false

    if (Configuration.JMDICT_LOCATION.isBlank() &&
        Configuration.KANJI_DICT_2_LOCATION.isBlank()
    ) {
        if (Configuration.SENTENCE_LOCATION.isBlank()) {
            println("No files provided, please specify the input file paths in Configuration.kt")
        } else {
            println("No output can be provided with only sentences supplied, please include JMdict in input")
        }

        prematureExit = true
    }

    if (Configuration.DICTIONARY_TOOLKIT_DIRECTORY.isBlank()) {
        println("No Apple Dictionary Development Kit path is provided. This is required to product a correct makefile.")
        prematureExit = true
    }

    if (Configuration.DICTIONARY_NAME.isBlank()) {
        println("No name for dictionary is specified, this will result in errors")
        prematureExit = true
    }

    if (Configuration.OUTPUT_DIRECTORY.isBlank()) {
        println("No output directory is specified, please specify it in Configuration.kt")
        prematureExit = true
    }

    if (Configuration.LANGUAGE.isEmpty()) {
        println("No output languages supplied, only Japanese entries will be output")
    }

    if (Configuration.DEBUG_OUTPUT) {
        println("Debug output is enabled! This will only print a limited subset of entries")
    }

    if (prematureExit) {
        println("One or more critical variables are missing. Please see above lines for details. Exiting")
        exitProcess(1)
    }
}

fun loadSentences() =
    if (Configuration.SENTENCE_LOCATION.isBlank()) {
        println("Skipping sentences (no file provided)")
        TatoebaSentences(NullIOInterface())
    } else {
        println("Loading sentences")
        TatoebaSentences(FileIOInterface(Configuration.SENTENCE_LOCATION))
    }

fun loadJmdict() =
    if (Configuration.JMDICT_LOCATION.isBlank()) {
        println("Skipping JMdict (no file provided)")
        null
    } else {
        println("Loading JMdict")
        JMDict(FileIOInterface(Configuration.JMDICT_LOCATION))
    }

fun loadKanjiDic() =
    if (Configuration.KANJI_DICT_2_LOCATION.isBlank()) {
        println("Skipping KanjiDic2 (no file provided)")
        null
    } else {
        println("Loading KanjiDic2")
        KanjiDic2(FileIOInterface(Configuration.KANJI_DICT_2_LOCATION))
    }
