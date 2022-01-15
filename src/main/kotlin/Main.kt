
import jmdict.JMDict
import kanjidic.KanjiDic2
import loader.FileLoader
import loader.NullLoader
import output.dictionaryapp.DictionaryAppOutput
import sentences.TatoebaSentences
import kotlin.system.exitProcess

fun main() {
    checkConfiguration()

    println("Preparing to create ${Configuration.DISPLAY_NAME}")
    println("Output languages:\n\t${Configuration.LANGUAGE.joinToString("\n\t")}")
    println("Output directory: \"${Configuration.OUTPUT_DIRECTORY}\"")

    val sentences = loadSentences()
    val jmDict = loadJmdict()
    val kanjiDic2 = loadKanjiDic()

    println("Generating dictionary app output")
    val dictionaryAppOutput = DictionaryAppOutput(jmDict, kanjiDic2, sentences, Configuration.LANGUAGE)

    println("Writing dictionary app output to disk")
    dictionaryAppOutput.writeAll(Configuration.OUTPUT_DIRECTORY)
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
        println("Tatoeba Tanaka Corpus sentence list not provided. Skipping.")
        TatoebaSentences(NullLoader())
    } else {
        println("Loading sentences")
        TatoebaSentences(FileLoader(Configuration.SENTENCE_LOCATION))
    }

fun loadJmdict() =
    if (Configuration.JMDICT_LOCATION.isBlank()) {
        println("JMdict file path is not provided. Skipping.")
        JMDict(NullLoader())
    } else {
        println("Loading JMdict")
        JMDict(FileLoader(Configuration.JMDICT_LOCATION))
    }

fun loadKanjiDic() =
    if (Configuration.KANJI_DICT_2_LOCATION.isBlank()) {
        println("KanjiDic2 file path is not provided, this is currently unused at this time so no worries. Skipping.")
        KanjiDic2(NullLoader())
    } else {
        println("Loading KanjiDic2")
        KanjiDic2(FileLoader(Configuration.KANJI_DICT_2_LOCATION))
    }
