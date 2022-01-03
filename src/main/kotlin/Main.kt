import common.ResourceChecker
import jmdict.Dictionary
import loader.FileLoader
import output.common.datatypes.JapaneseEntry
import output.web.HtmlOutput
import sentences.TatoebaSentences

fun main() {
    val baseMemory = ResourceChecker.memoryUsage()

    println("Loading sentences")
    val sentences = TatoebaSentences(FileLoader(Configuration.SENTENCE_LOCATION))
    val sentencesMemory = ResourceChecker.memoryUsage() - baseMemory

    println("Loading dictionary")
    val dictionary = Dictionary(FileLoader(Configuration.JMDICT_LOCATION))
    val dictionaryMemory = ResourceChecker.memoryUsage() - (baseMemory + sentencesMemory)

    println("Loading kanji dictionary")
    val kanjiDictionary = kanjidic.Dictionary(FileLoader(Configuration.KANJI_DICT_2_LOCATION))
    val kanjiDictionaryMemory = ResourceChecker.memoryUsage() - (baseMemory + sentencesMemory + dictionaryMemory)

    println("Generating output dictionary")
    val outputDictionary = output.common.Dictionary(dictionary, kanjiDictionary, sentences)

    val htmlOutput = HtmlOutput("/Users/jackson/Desktop/output")
    outputDictionary.entries.subList(0, 10).forEach { htmlOutput.render(it as JapaneseEntry) }

    println("Memory usage:\n\tBase: $baseMemory MB\n" +
            "\tSentences: $sentencesMemory MB\n" +
            "\tDictionary: $dictionaryMemory MB\n" +
            "\tKanji Dictionary: $kanjiDictionaryMemory MB\n" +
            "\tTotal: ${ResourceChecker.memoryUsage()} MB")
}
