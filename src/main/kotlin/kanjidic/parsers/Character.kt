package kanjidic.parsers

import kanjidic.datatypes.CharacterElement
import xmlreader.Tag

object Character {
    private const val LITERAL = "literal"
    private const val CODEPOINT = "codepoint"
    private const val CODEPOINT_VALUE = "cp_value"
    private const val RADICAL = "radical"
    private const val RADICAL_VALUE = "rad_value"
    private const val MISC = "misc"
    private const val READING_MEANING = "reading_meaning"
    private const val READING_MEANING_GROUP = "rmgroup"
    private const val NANORI = "nanori"

    fun parse(tag: Tag) =
        CharacterElement(
            literal = literal(tag),
            codepoint = codepoint(tag),
            radical = radical(tag),
            misc = misc(tag),
            readingMeaning = readingMeaning(tag),
            nanori = nanori(tag)
        )

    private fun literal(tag: Tag) =
        tag.childrenWithTagName(LITERAL).first().text()

    private fun codepoint(tag: Tag) =
        tag.childrenWithTagName(CODEPOINT).first().childrenWithTagName(CODEPOINT_VALUE).map { Codepoint.parse(it) }

    private fun radical(tag: Tag) =
        tag.childrenWithTagName(RADICAL).first().childrenWithTagName(RADICAL_VALUE).map { Radical.parse(it) }

    private fun misc(tag: Tag) = Misc.parse(tag.childrenWithTagName(MISC).first())

    private fun readingMeaning(tag: Tag) =
        tag.childrenWithTagName(READING_MEANING).firstOrNull()?.childrenWithTagName(READING_MEANING_GROUP)?.map {
            ReadingMeaning.parse(it)
        }

    private fun nanori(tag: Tag) =
        tag.childrenWithTagName(READING_MEANING).firstOrNull()?.childrenWithTagName(NANORI)?.map { it.text() }
}
