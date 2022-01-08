import common.Language

object Configuration {
    /* Input Configuration */
    // The JMdict dictionary XML file, either the full dictinary or the english entries only will work
    const val JMDICT_LOCATION = "/Users/jackson/Desktop/japanese-dictionary/input/JMdict"

    // KanjiDict2 XML file
    const val KANJI_DICT_2_LOCATION = "/Users/jackson/Desktop/japanese-dictionary/input/kanjidic2.xml"

    // Tatoeba Tanaka Corpus sentences. Ideally use the verified sentences (not sure if other files will work)
    const val SENTENCE_LOCATION = "/Users/jackson/Desktop/japanese-dictionary/input/examples_s"

    /* Output Configuration */
    const val OUTPUT_DIRECTORY = "/Users/Jackson/Desktop/build"
    const val DICTIONARY_NAME = "Japanese-English Dictionary"
    const val DICTIONARY_TOOLKIT_DIRECTORY = "/Users/jackson/Desktop/Dictionary Development Kit"

    // The output dictionary will be indented correctly. This can break the Apple compiler if the file is too
    // large (~>250mb)
    const val PRETTY_PRINT_OUTPUT = true

    // Substitutes human readable element classes for single-letter names
    const val SIMPLIFY_TAG_CLASS_NAMES = true

    // Will only output the below subset of the dictionary for testing purposes
    const val DEBUG_OUTPUT = false

    // The entries to create when [DEBUG_OUTPUT] is enabled
    val DEBUG_OUTPUT_ENTRIES = listOf("赤", "青", "緑")

    // The two and three letter language abbreviation to create a dictionary for
    val LANGUAGE = listOf(Language.ENGLISH)
}
