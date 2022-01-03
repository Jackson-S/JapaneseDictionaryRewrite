package output.common.datatypes

import common.Language
import output.common.enums.EntryTypeEnum
import sentences.datatypes.Sentence

data class JapaneseEntry(
    override val title: String,

    /**
     * The kanji contained in the entry's title
     */
    val containedKanji: List<String>?,

    /**
     * A series of sentences containing the title word
     */
    val sentences: List<Sentence>?,

    /**
     * The possible Japanese readings of the title
     */
    val kanaReadings: List<Reading>,

    /**
     * The possible kanji used to represent the title
     */
    val kanjiReadings: List<Reading>?,

    /**
     * A foreign-language translation or explanation of the title
     */
    val definitions: List<Definition>
) : Entry {
    /**
     * Denotes that the title word is in Japanese
     */
    override val language: Language
        get() = Language.JAPANESE

    /**
     * Denotes that this page is a translation or definition of a word or phrase
     */
    override val entryType: EntryTypeEnum
        get() = EntryTypeEnum.DEFINITION
}
