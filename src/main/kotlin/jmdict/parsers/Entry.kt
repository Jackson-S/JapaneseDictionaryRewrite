package jmdict.parsers

import jmdict.datatypes.EntryElement
import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.SenseElement
import jmdict.exceptions.MissingFieldException
import xmlreader.Tag

object Entry {
    const val ENTRY_SEQUENCE = "ent_seq"
    const val KANJI_ELEMENT = "k_ele"
    const val READING_ELEMENT = "r_ele"
    const val SENSE = "sense"

    fun parse(element: Tag): EntryElement =
        EntryElement(
            entrySequence = sequence(element),
            kanjiElement = kanji(element),
            readingElement = reading(element),
            senseElement = sense(element)
        )

    private fun sequence(element: Tag): Int =
        element.childrenWithTagName(ENTRY_SEQUENCE).firstOrNull()?.text()?.toInt() ?: throw MissingFieldException(ENTRY_SEQUENCE)

    private fun kanji(element: Tag): List<KanjiElement>? =
        element.childrenWithTagName(KANJI_ELEMENT).map { Kanji.parse(it) }.ifEmpty { null }

    private fun reading(element: Tag): List<ReadingElement> =
        element.childrenWithTagName(READING_ELEMENT).map { Reading.parse(it) }

    private fun sense(element: Tag): List<SenseElement> =
        element.childrenWithTagName(SENSE).map { Sense.parse(it) }
}
