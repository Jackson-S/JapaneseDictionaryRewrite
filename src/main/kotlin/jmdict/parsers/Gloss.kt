package jmdict.parsers

import common.Language
import jmdict.datatypes.GlossElement
import xmlreader.Tag

object Gloss {
    private object Attribute {
        const val LANGUAGE = "xml:lang"
        const val GENDER = "g_gend"
        const val TYPE = "g_type"
    }
    private const val PRIORITY = "pri"

    fun parse(element: Tag) =
        GlossElement(
            element = text(element),
            language = language(element),
            gender = gender(element),
            type = type(element),
            priority = priority(element),
        )

    private fun language(element: Tag) =
        Language.fromCode(element.properties().getOrDefault(Attribute.LANGUAGE, Language.ENGLISH.code3))

    private fun gender(element: Tag) =
        element.properties()[Attribute.GENDER]

    private fun type(element: Tag) =
        element.properties()[Attribute.TYPE]

    private fun text(element: Tag) =
        element.text()

    private fun priority(element: Tag) =
        element.childrenWithTagName(PRIORITY).map { it.text() }.ifEmpty { null }
}
