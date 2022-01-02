package jmdict.parsers

import jmdict.datatypes.GlossElement
import jmdict.map
import org.w3c.dom.Element

object Gloss {
    private object Attribute {
        const val LANGUAGE = "xml:lang"
        const val GENDER = "g_gend"
        const val TYPE = "g_type"
    }
    private const val PRIORITY = "pri"

    fun parse(element: Element) =
        GlossElement(
            language = language(element),
            gender = gender(element),
            type = type(element),
            priority = priority(element),
        )

    private fun language(element: Element) =
        if (element.hasAttribute(Attribute.LANGUAGE))
            element.getAttribute(Attribute.LANGUAGE)
        else
            "eng"

    private fun gender(element: Element) =
        if (element.hasAttribute(Attribute.GENDER))
            element.getAttribute(Attribute.GENDER)
        else
            null

    private fun type(element: Element) =
        if (element.hasAttribute(Attribute.GENDER))
            element.getAttribute(Attribute.GENDER)
        else
            null

    private fun priority(element: Element) =
        element.getElementsByTagName(PRIORITY).map { it.textContent }.ifEmpty { null }
}
