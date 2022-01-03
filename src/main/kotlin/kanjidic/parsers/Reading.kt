package kanjidic.parsers

import kanjidic.datatypes.ReadingElement
import kanjidic.enums.ReadingTypeEnum
import xmlreader.Tag

object Reading {
    private const val READING_TYPE = "r_type"
    private const val ON_TYPE = "on_type"
    private const val READING_STATUS = "r_status"

    fun parse(tag: Tag) =
        ReadingElement(
            value = tag.text(),
            readingType = ReadingTypeEnum.from(tag.properties()[READING_TYPE]!!),
            onType = tag.properties()[ON_TYPE],
            readingStatus = tag.properties()[READING_STATUS]
        )
}
