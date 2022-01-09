import common.Language

object Configuration {
    /* Input Configuration */
    // The JMdict dictionary XML file, either the full dictinary or the english entries only will work
    // Can be obtained from
    // http://www.edrdg.org/wiki/index.php/JMdict-EDICT_Dictionary_Project#CURRENT_VERSION_.26_DOWNLOAD
    // (download JMdict.gz and extract then put the location below)
    const val JMDICT_LOCATION = "/Users/jackson/Desktop/japanese-dictionary/input/JMdict"

    // KanjiDict2 XML file
    // Can be obtained from http://www.edrdg.org/wiki/index.php/KANJIDIC_Project#Introduction
    // (download the UTF-8 version)
    const val KANJI_DICT_2_LOCATION = "/Users/jackson/Desktop/japanese-dictionary/input/kanjidic2.xml"

    // Tatoeba Tanaka Corpus sentences.
    // It can be obtained from here https://dict.longdo.com/about/hintcontents/tanakacorpus.html
    // Download the complete version (UTF-8), or the subset and then convert it to UTF-8 before use
    const val SENTENCE_LOCATION = "/Users/jackson/Desktop/japanese-dictionary/input/examples.utf"

    /* Output Configuration */
    const val OUTPUT_DIRECTORY = "/Users/Jackson/Desktop/build"

    // The output bundle name
    const val DICTIONARY_NAME = "Japanese-English Dictionary"

    // The name displayed in Dictionary.app's menu bar
    const val DISPLAY_NAME = "Japanese-English Dictionary"

    // The path containing the Apple dictionary development kit files
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
