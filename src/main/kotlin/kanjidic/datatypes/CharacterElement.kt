package kanjidic.datatypes

import common.Entry

// literal,codepoint, radical, misc, dic_number?, query_code?, reading_meaning?

data class CharacterElement(
    /**
     * The character itself in UTF8 coding.
     */
    val literal: String,

    /**
     * The codepoint element states the code of the character in the various
     * character set standards.
     */
    val codepoint: List<CodepointElement>,

    /**
     * The radical number, in the range 1 to 214. The particular
     * classification type is stated in the rad_type attribute.
     */
    val radical: List<RadicalElement>,

    val misc: MiscElement,

    /**
     * The readings for the kanji in several languages, and the meanings, also
     * in several languages. The readings and meanings are grouped to enable
     * the handling of the situation where the meaning is differentiated by
     * reading.
     */
    val readingMeaning: List<ReadingMeaningElement>?,

    /**
     * Japanese readings that are now only associated with names.
     */
    val nanori: List<String>?
) : Entry {
    override val headWord: String
        get() = literal
}
