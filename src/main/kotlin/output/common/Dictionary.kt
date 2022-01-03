package output.common

import common.Language
import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.SenseElement
import output.common.datatypes.*
import sentences.TatoebaSentences

class Dictionary(
    val jmDict: jmdict.Dictionary,
    val kanjiDic: kanjidic.Dictionary,
    val sentences: TatoebaSentences
) {
    val entries: MutableList<Entry> = mutableListOf()

    init {
        // Create dictionary pages
        val jmDictEntries = jmDict.entries.map {
            JapaneseEntry(
                title = it.headWord,
                containedKanji = it.kanjiElement?.flatMap { kanji -> containedKanji(kanji) },
                sentences = sentences.sentences(it.headWord),
                kanaReadings = it.readingElement.flatMap { kanaReading -> kanaReadings(kanaReading) },
                kanjiReadings = it.kanjiElement?.flatMap { kanjiReading -> kanjiReadings(kanjiReading) },
            )
        }

        entries.addAll(jmDictEntries)

        entries.add(ForeignEntry("TEST", Language.ENGLISH, listOf(Translation("Test", listOf("test"), listOf("test")))))
    }

    private fun containedKanji(kanjiElement: KanjiElement): List<String> =
        kanjiElement.element.distinctBy { it in kanjiDic.entries() }

    private fun kanaReadings(readingElement: ReadingElement) =
        readingElement.element.map { element -> Reading(element, readingElement.information?.map { it }) }

    private fun kanjiReadings(kanjiElement: KanjiElement) =
        kanjiElement.element.map { element -> Reading(element, kanjiElement.information?.map { it }) }
}