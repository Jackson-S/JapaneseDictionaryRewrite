package jmdict.parsers

import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.Reference
import jmdict.enums.EntryElement
import jmdict.exceptions.MissingFieldException
import jmdict.map
import org.w3c.dom.Element

object Reading {
    private const val ELEMENT = "reb"
    private const val NO_KANJI = "re_nokanji"
    private const val RESTRICTED = "re_restr"
    private const val INFORMATION = "re_inf"
    private const val PRIORITY = "re_pri"

    fun parse(element: Element): ReadingElement =
        ReadingElement(
            element = element(element),
            information = information(element),
            priority = priority(element),
            noKanji = noKanji(element),
            readingRestricted = readingRestricted(element)
        )

    fun postProcess(entry: EntryElement) {
        entry.readingElement.forEach { reading ->
            reading.readingRestricted?.forEach { restrictedReading ->
                restrictedReading.value = entry.kanjiElement!!.first { kanji ->
                    kanji.element.contains(restrictedReading.referralText)
                }
            }
        }
    }

    private fun element(element: Element) =
        element.getElementsByTagName(ELEMENT).map { it.textContent }.ifEmpty { throw MissingFieldException(ELEMENT) }

    private fun information(element: Element) =
        element.getElementsByTagName(INFORMATION).map { it.textContent }.ifEmpty { null }

    private fun priority(element: Element) =
        element.getElementsByTagName(PRIORITY).map { it.textContent }.ifEmpty { null }

    private fun noKanji(element: Element) =
        element.getElementsByTagName(NO_KANJI).length != 0

    private fun readingRestricted(element: Element) =
        element.getElementsByTagName(RESTRICTED).map { Reference<KanjiElement>(it.textContent) }.ifEmpty { null }
}
