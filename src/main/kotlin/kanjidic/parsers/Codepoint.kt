package kanjidic.parsers

import kanjidic.datatypes.CodepointElement
import kanjidic.enums.CodepointCharsetEnum
import xmlreader.Tag

object Codepoint {
    private const val CODEPOINT_TYPE = "cp_type"

    fun parse(tag: Tag) =
        CodepointElement(
            value = tag.text(),
            charset = CodepointCharsetEnum.from(tag.properties()[CODEPOINT_TYPE]!!)
        )
}
