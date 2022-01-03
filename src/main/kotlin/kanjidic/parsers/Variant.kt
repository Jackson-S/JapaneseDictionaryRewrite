package kanjidic.parsers

import kanjidic.datatypes.VariantElement
import kanjidic.enums.VariantTypeEnum
import xmlreader.Tag

object Variant {
    private const val VARIANT_TYPE = "var_type"

    fun parse(tag: Tag) =
        VariantElement(
            index = tag.text(),
            type = VariantTypeEnum.from(tag.properties()[VARIANT_TYPE]!!)
        )
}
