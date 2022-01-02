package jmdict.parsers

import jmdict.datatypes.SourceLanguageElement
import org.w3c.dom.Element

object SourceLanguage {

    object Attribute {
        const val LANGUAGE = "xml:lang"
        const val PARTIAL = "ls_type"
        const val WASEIEIGO = "ls_wasei"
    }

    fun parse(element: Element) =
        SourceLanguageElement(
            language = language(element),
            information = information(element),
            partial = partial(element),
            waseiEigo = waseiEigo(element)
        )

    private fun language(element: Element) =
        if (element.hasAttribute(Attribute.LANGUAGE))
            element.getAttribute(Attribute.LANGUAGE)
        else
            "eng"

    private fun information(element: Element) =
        element.textContent.ifBlank { null }

    private fun partial(element: Element) = element.hasAttribute(Attribute.PARTIAL)

    private fun waseiEigo(element: Element) = element.hasAttribute(Attribute.WASEIEIGO)
}
