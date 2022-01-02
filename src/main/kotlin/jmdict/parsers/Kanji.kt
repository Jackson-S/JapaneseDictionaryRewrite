package jmdict.parsers

import jmdict.datatypes.KanjiElement
import jmdict.exceptions.MissingFieldException
import jmdict.map
import org.w3c.dom.Element

object Kanji {
    private const val ELEMENT = "keb"
    private const val INFORMATION = "ke_inf"
    private const val PRIORITY = "ke_pri"

    fun parse(element: Element): KanjiElement =
        KanjiElement(
            element = element(element),
            information = information(element),
            priority = priority(element)
        )

    private fun element(element: Element): List<String> {
        return element.getElementsByTagName(ELEMENT).map { it.textContent }.ifEmpty {
            throw MissingFieldException(
                ELEMENT
            )
        }
    }

    private fun information(element: Element) =
        element.getElementsByTagName(INFORMATION).map { it.textContent }.ifEmpty { null }

    private fun priority(element: Element) =
        element.getElementsByTagName(PRIORITY).map { it.textContent }.ifEmpty { null }
}
