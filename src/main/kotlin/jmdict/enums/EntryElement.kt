package jmdict.enums

import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.Referrable
import jmdict.datatypes.SenseElement

/**
 * Entries consist of kanji elements, reading elements,
 * general information and sense elements. Each entry must have at
 * least one reading element and one sense element. Others are optional.
 */
data class EntryElement(
    val entrySequence: Int, // A unique numeric sequence number for each entry
    val kanjiElement: List<KanjiElement>?,
    val readingElement: List<ReadingElement>,
    val senseElement: List<SenseElement>
) : Referrable
