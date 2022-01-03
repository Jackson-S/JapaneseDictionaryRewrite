package jmdict.datatypes

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
) : Referrable
