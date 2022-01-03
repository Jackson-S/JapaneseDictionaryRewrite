import jmdict.Dictionary
import loader.FileLoader
import sentences.SentenceParser

fun main() {
    val baseMemory = ResourceChecker.memoryUsage()

    println("Loading sentences")
    val sentences = SentenceParser(
        FileLoader(Configuration.SENTENCE_LOCATION),
        FileLoader(Configuration.SENTENCE_INDEX_LOCATION),
        Configuration.LANGUAGE
    )
    val sentencesMemory = ResourceChecker.memoryUsage() - baseMemory

    println("Loading dictionary")
    val dictionary = Dictionary(FileLoader(Configuration.JMDICT_LOCATION))
    val dictionaryMemory = ResourceChecker.memoryUsage() - (baseMemory + sentencesMemory)

    println("Loading kanji dictionary")
    val kanjiDictionary = kanjidic.Dictionary(FileLoader(Configuration.KANJI_DICT_2_LOCATION))
    val kanjiDictionaryMemory = ResourceChecker.memoryUsage() - (baseMemory + sentencesMemory + dictionaryMemory)

    println("Memory usage:\n\tBase: $baseMemory MB\n" +
            "\tSentences: $sentencesMemory MB\n" +
            "\tDictionary: $dictionaryMemory MB\n" +
            "\tKanji Dictionary: $kanjiDictionaryMemory MB\n" +
            "\tTotal: ${ResourceChecker.memoryUsage()} MB")
}
