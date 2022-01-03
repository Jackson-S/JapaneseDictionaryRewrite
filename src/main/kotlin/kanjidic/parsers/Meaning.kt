package kanjidic.parsers

import common.Language
import kanjidic.datatypes.MeaningElement
import xmlreader.Tag

object Meaning {
    private const val LANGUAGE = "m_lang"

    fun parse(tag: Tag) =
        MeaningElement(
            value = tag.text(),
            language = tag.properties()[LANGUAGE]?.let { Language.fromCode(it) } ?: Language.ENGLISH
        )
}
