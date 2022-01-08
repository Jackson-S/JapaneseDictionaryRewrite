package jmdict.parsers

import jmdict.datatypes.EntryElement
import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.Reference
import jmdict.enums.ReadingInformationEnum
import jmdict.exceptions.MissingFieldException
import xmlreader.Tag

object Reading {
    private const val ELEMENT = "reb"
    private const val NO_KANJI = "re_nokanji"
    private const val RESTRICTED = "re_restr"
    private const val INFORMATION = "re_inf"
    private const val PRIORITY = "re_pri"

    fun parse(element: Tag): ReadingElement =
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
                restrictedReading.value = entry.kanjiElements!!.first { kanji ->
                    kanji.element.contains(restrictedReading.referralText)
                }
            }
        }
    }

    private fun element(element: Tag) =
        element.childrenWithTagName(ELEMENT).map { it.text() }.ifEmpty { throw MissingFieldException(ELEMENT) }

    private fun information(element: Tag) =
        element.childrenWithTagName(INFORMATION).map { ReadingInformationEnum.from(it.text()) }.ifEmpty { null }

    private fun priority(element: Tag) =
        element.childrenWithTagName(PRIORITY).map { it.text() }.ifEmpty { null }

    private fun noKanji(element: Tag) =
        element.childrenWithTagName(NO_KANJI).isNotEmpty()

    private fun readingRestricted(element: Tag) =
        element.childrenWithTagName(RESTRICTED).map { Reference<KanjiElement>(it.text()) }.ifEmpty { null }
}
