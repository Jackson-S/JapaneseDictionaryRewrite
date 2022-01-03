package kanjidic.parsers

import kanjidic.datatypes.RadicalElement
import kanjidic.enums.RadicalTypeEnum
import xmlreader.Tag

object Radical {
    private const val RADICAL_TYPE = "rad_type"

    fun parse(tag: Tag) =
        RadicalElement(
            value = tag.text().toInt(),
            type = RadicalTypeEnum.from(tag.properties()[RADICAL_TYPE]!!)
        )
}
