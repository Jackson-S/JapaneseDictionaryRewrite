package kanjidic.parsers

import kanjidic.datatypes.MiscElement
import xmlreader.Tag

object Misc {
    private const val GRADE = "grade"
    private const val STROKE_COUNT = "stroke_count"
    private const val VARIANT = "variant"
    private const val FREQUENCY_OF_USE = "freq"
    private const val RADICAL_NAME = "rad_name"
    private const val JLPT_LEVEL = "jlpt"

    fun parse(tag: Tag) =
        MiscElement(
            grade = grade(tag),
            strokeCount = strokeCount(tag),
            variant = variant(tag),
            frequencyOfUse = frequencyOfUse(tag),
            radicalName = radicalName(tag),
            jlptLevel = jlptLevel(tag)
        )

    private fun grade(tag: Tag) = tag.childrenWithTagName(GRADE).firstOrNull()?.text()?.toInt()

    private fun strokeCount(tag: Tag) = tag.childrenWithTagName(STROKE_COUNT).map { it.text().toInt() }

    private fun variant(tag: Tag) = tag.childrenWithTagName(VARIANT).map { Variant.parse(it) }

    private fun frequencyOfUse(tag: Tag) = tag.childrenWithTagName(FREQUENCY_OF_USE).firstOrNull()?.text()?.toInt()

    private fun radicalName(tag: Tag) = tag.childrenWithTagName(RADICAL_NAME).map { it.text() }.ifEmpty { null }

    private fun jlptLevel(tag: Tag) = tag.childrenWithTagName(JLPT_LEVEL).firstOrNull()?.text()?.toInt()
}
