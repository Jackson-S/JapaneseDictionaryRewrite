package jmdict.parsers

import jmdict.datatypes.SourceLanguageElement
import xmlreader.Tag

object SourceLanguage {

    object Attribute {
        const val LANGUAGE = "xml:lang"
        const val PARTIAL = "ls_type"
        const val WASEIEIGO = "ls_wasei"
    }

    fun parse(element: Tag) =
        SourceLanguageElement(
            language = language(element),
            information = information(element),
            partial = partial(element),
            waseiEigo = waseiEigo(element)
        )

    private fun language(element: Tag) =
        element.properties().getOrDefault(Attribute.LANGUAGE, "eng")

    private fun information(element: Tag) = element.text().ifBlank { null }

    private fun partial(element: Tag) = element.properties().containsKey(Attribute.PARTIAL)

    private fun waseiEigo(element: Tag) = element.properties().containsKey(Attribute.WASEIEIGO)
}
