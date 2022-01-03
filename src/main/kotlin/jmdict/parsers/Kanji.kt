package jmdict.parsers

import jmdict.datatypes.KanjiElement
import jmdict.exceptions.MissingFieldException
import xmlreader.Tag

object Kanji {
    private const val ELEMENT = "keb"
    private const val INFORMATION = "ke_inf"
    private const val PRIORITY = "ke_pri"

    fun parse(element: Tag): KanjiElement =
        KanjiElement(
            element = element(element),
            information = information(element),
            priority = priority(element)
        )

    private fun element(element: Tag): List<String> =
        element.childrenWithTagName(ELEMENT).map { it.text() }.ifEmpty { throw MissingFieldException(ELEMENT) }

    private fun information(element: Tag) =
        element.childrenWithTagName(INFORMATION).map { it.text() }.ifEmpty { null }

    private fun priority(element: Tag) =
        element.childrenWithTagName(PRIORITY).map { it.text() }.ifEmpty { null }
}
