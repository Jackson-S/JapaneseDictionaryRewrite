package output.dictionary.templates.japanese

import jmdict.datatypes.EntryElement
import jmdict.enums.InformationEnum
import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.p
import output.dictionary.templates.HtmlClass

internal object SubHeader {
    fun BODY.subheader(entry: EntryElement) {
        if (entry.kanaReadings().size > 1 || (entry.kanjiReadings() ?: emptyList()).size > 1) {
            div(HtmlClass.SUB_HEADING) {
                kanaReadings(entry)
                kanjiReadings(entry)
            }
        }
    }

    private fun DIV.kanaReadings(entry: EntryElement) {
        val readings = entry.kanaReadings()

        // If there is only a single reading it will be covered in the header
        if (readings.size <= 1) { return }

        readings("Readings", readings)
    }

    private fun DIV.kanjiReadings(entry: EntryElement) {
        val kanji = entry.kanjiReadings()

        if (kanji == null || kanji.size <= 1) return

        readings("Alternate Kanji", kanji)
    }

    private fun DIV.readings(header: String, readings: List<Pair<String, List<InformationEnum>?>>) {
        h3(HtmlClass.MOUSEOVER_DISABLE) { +header }

        p { +readings.exceptFirst().joinToString("、") { it.first } }
    }

    private fun EntryElement.kanaReadings() =
        readingElement.flatMap { reading ->
            reading.element.map { element ->
                Pair(element, reading.information?.map { it })
            }
        }

    private fun EntryElement.kanjiReadings() =
        kanjiElements?.flatMap { kanji ->
            kanji.element.map { element ->
                Pair(element, kanji.information?.map { it })
            }
        }

    private fun <T> List<T>.exceptFirst() = subList(1, size)
}
