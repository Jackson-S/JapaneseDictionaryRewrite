package jmdict.parsers

import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.SenseElement
import jmdict.enums.EntryElement
import jmdict.exceptions.MissingFieldException
import jmdict.first
import jmdict.map
import org.w3c.dom.Element

object Entry {
    const val ENTRY_SEQUENCE = "ent_seq"
    const val KANJI_ELEMENT = "k_ele"
    const val READING_ELEMENT = "r_ele"
    const val SENSE = "sense"

    fun parse(element: Element): EntryElement =
        EntryElement(
            entrySequence = sequence(element),
            kanjiElement = kanji(element),
            readingElement = reading(element),
            senseElement = sense(element)
        )

    private fun sequence(element: Element): Int =
        element.getElementsByTagName(ENTRY_SEQUENCE).first()?.textContent?.toInt() ?: throw MissingFieldException(ENTRY_SEQUENCE)

    private fun kanji(element: Element): List<KanjiElement>? =
        element.getElementsByTagName(KANJI_ELEMENT).map { Kanji.parse(it as Element) }.ifEmpty { null }

    private fun reading(element: Element): List<ReadingElement> =
        element.getElementsByTagName(READING_ELEMENT).map { Reading.parse(it as Element) }

    private fun sense(element: Element): List<SenseElement> =
        element.getElementsByTagName(SENSE).map { Sense.parse(it as Element) }
}
