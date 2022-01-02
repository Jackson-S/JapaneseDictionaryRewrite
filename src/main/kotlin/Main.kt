import jmdict.Dictionary
import loader.FileLoader
import sentences.SentenceParser

fun main(args: Array<String>) {
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
    val dictionaryMemory = ResourceChecker.memoryUsage() - baseMemory + sentencesMemory

    println("Memory usage:\n\tBase: $baseMemory MB\n\tSentences: $sentencesMemory MB\n\tDictionary: $dictionaryMemory MB")
}
