package jmdict.datatypes

import common.Entry

/**
 * Entries consist of kanji elements, reading elements,
 * general information and sense elements. Each entry must have at
 * least one reading element and one sense element. Others are optional.
 */
data class EntryElement(
    /**
     * A unique numeric sequence number for each entry
     */
    val entrySequence: Int,
    val kanjiElement: List<KanjiElement>?,
    val readingElement: List<ReadingElement>,
    val senseElement: List<SenseElement>
) : Referrable, Entry {

    /**
     * If present the first Kanji element acts as the headword, otherwise the first reading element is the headword
     */
    override val headWord: String
        get() = kanjiElement?.let { it.first().element.first() } ?: readingElement.first().element.first()
}
