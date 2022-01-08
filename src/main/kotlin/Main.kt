import common.ResourceChecker
import jmdict.JMDict
import loader.FileLoader
import output.dictionaryapp.DictionaryAppOutput
import sentences.TatoebaSentences

fun main() {
    val baseMemory = ResourceChecker.memoryUsage()

    println("Loading sentences")
    val sentences = TatoebaSentences(FileLoader(Configuration.SENTENCE_LOCATION))
    val sentencesMemory = ResourceChecker.memoryChange()

    println("Loading dictionary")
    val dictionary = JMDict(FileLoader(Configuration.JMDICT_LOCATION))
    val dictionaryMemory = ResourceChecker.memoryChange()

    println("Loading kanji dictionary")
    val kanjiDictionary = kanjidic.KanjiDic2(FileLoader(Configuration.KANJI_DICT_2_LOCATION))
    val kanjiDictionaryMemory = ResourceChecker.memoryChange()

    println("Generating dictionary app output")
    val dictionaryAppOutput = DictionaryAppOutput(dictionary, kanjiDictionary, sentences, Configuration.LANGUAGE)
    val outputMemory = ResourceChecker.memoryChange()

    println("Writing dictionary app output to disk")
    dictionaryAppOutput.writeAll(Configuration.OUTPUT_DIRECTORY)

    println(
        "Memory usage:\n\tBase: $baseMemory MB\n" +
        "\tSentences: $sentencesMemory MB\n" +
        "\tDictionary: $dictionaryMemory MB\n" +
        "\tKanji Dictionary: $kanjiDictionaryMemory MB\n" +
        "\tOutput: $outputMemory MB\n" +
        "\tTotal: ${ResourceChecker.memoryUsage()} MB"
    )
}
