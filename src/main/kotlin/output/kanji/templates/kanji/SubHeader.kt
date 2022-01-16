package output.kanji.templates.kanji

import kanjidic.datatypes.ReadingElement
import kanjidic.datatypes.ReadingMeaningElement
import kanjidic.enums.ReadingTypeEnum
import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.p
import output.dictionary.templates.HtmlClass

internal object SubHeader {
    fun BODY.subheader(readingMeaning: ReadingMeaningElement) {
        div(HtmlClass.SUB_HEADING) {
            onReadings(readingMeaning)
            kunReadings(readingMeaning)
            pinyinReadings(readingMeaning)
            hangulReadings(readingMeaning)
        }
    }

    private fun DIV.onReadings(readingMeaning: ReadingMeaningElement) {
        val readings = readingMeaning.reading?.filter { it.readingType == ReadingTypeEnum.JAPANESE_ON }
        if (!readings.isNullOrEmpty()) {
            readings("On Readings", readings, "、")
        }
    }

    private fun DIV.kunReadings(readingMeaning: ReadingMeaningElement) {
        val readings = readingMeaning.reading?.filter { it.readingType == ReadingTypeEnum.JAPANESE_KUN }
        if (!readings.isNullOrEmpty()) {
            readings("Kun Readings", readings, "、")
        }
    }

    private fun DIV.pinyinReadings(readingMeaning: ReadingMeaningElement) {
        val readings = readingMeaning.reading?.filter { it.readingType == ReadingTypeEnum.PINYIN }
        if (!readings.isNullOrEmpty()) {
            readings("Pinyin Readings", readings, ", ")
        }
    }

    private fun DIV.hangulReadings(readingMeaning: ReadingMeaningElement) {
        val readings = readingMeaning.reading?.filter { it.readingType == ReadingTypeEnum.KOREAN_HANGUL }
        if (!readings.isNullOrEmpty()) {
            readings("Hangul Readings", readings, " ")
        }
    }

    private fun DIV.readings(header: String, readings: List<ReadingElement>, separator: String) {
        h3(HtmlClass.MOUSEOVER_DISABLE) { +header }
        p { +readings.joinToString(separator) { it.element } }
    }
}
