package kanjidic.parsers

import kanjidic.datatypes.ReadingMeaningElement
import xmlreader.Tag

object ReadingMeaning {
    private const val READING = "reading"
    private const val MEANING = "meaning"

    fun parse(tag: Tag) =
        ReadingMeaningElement(
            reading = tag.childrenWithTagName(READING).map { Reading.parse(it) },
            meaning = tag.childrenWithTagName(MEANING).map { Meaning.parse(it) }
        )
}
