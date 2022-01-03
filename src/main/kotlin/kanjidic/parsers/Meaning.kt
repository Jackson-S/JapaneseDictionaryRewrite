package kanjidic.parsers

import kanjidic.datatypes.MeaningElement
import xmlreader.Tag

object Meaning {
    private const val LANGUAGE = "xml:lang"

    fun parse(tag: Tag) =
        MeaningElement(
            value = tag.text(),
            language = tag.properties()[LANGUAGE]?.let { Language.fromCode(it) } ?: Language.ENGLISH
        )
}
