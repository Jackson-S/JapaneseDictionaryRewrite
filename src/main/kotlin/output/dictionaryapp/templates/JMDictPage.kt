package output.dictionaryapp.templates

import common.Language
import jmdict.datatypes.EntryElement
import kotlinx.html.BODY
import kotlinx.html.P
import kotlinx.html.RUBY
import kotlinx.html.SECTION
import kotlinx.html.article
import kotlinx.html.body
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.h1
import kotlinx.html.h3
import kotlinx.html.html
import kotlinx.html.p
import kotlinx.html.rt
import kotlinx.html.ruby
import kotlinx.html.section
import kotlinx.html.summary
import org.w3c.dom.Document
import sentences.datatypes.Sentence

class JMDictPage(
    private val document: Document,
    private val language: List<Language>
) {
    private fun BODY.title(entry: EntryElement) {
        section(Stylesheet.HEADER) {
            h1(Stylesheet.PAGE_TITLE) { +entry.headWord }
            if (!entry.headReading.isNullOrBlank()) {
                h1(Stylesheet.PRIMARY_READING) { +entry.headReading!! }
            }
        }
    }

    private fun SECTION.kanaReadings(entry: EntryElement) {
        val readings = entry.kanaReadings()

        // If there is only a single reading it will be covered in the header
        if (readings.size <= 1) { return }

        section(Stylesheet.READINGS) {
            readings("Readings", readings)
        }
    }

    private fun SECTION.kanjiReadings(entry: EntryElement) {
        val kanji = entry.kanjiReadings()

        if (kanji == null || kanji.size <= 1) return

        section(Stylesheet.READINGS) {
            readings("Alternate Kanji", kanji)
        }
    }

    private fun SECTION.readings(header: String, readings: List<Pair<String, List<String>?>>) {
        h3(Stylesheet.SECTION_HEADING) {
            attributes["apple_mouseover_disable"] = "1"
            +header
        }

        readings.exceptFirst().forEach { reading ->
            div(Stylesheet.READING) {
                p { +reading.first }
                reading.second?.forEach { info ->
                    div(Stylesheet.BADGE) { +info }
                }
            }
        }
    }

    private fun BODY.sentences(sentences: List<Sentence>?, limit: Int = 3) {
        if (!sentences.isNullOrEmpty()) {
            details(Stylesheet.SENTENCES) {
                summary(Stylesheet.SECTION_HEADING) { +"Example Sentences" }

                sentences.first(limit).forEach { sentence ->
                    article(Stylesheet.SENTENCE) {
                        p { furiganaSentence(sentence) }
                        p { +sentence.english }
                    }
                }
            }
        }
    }

    private fun P.furiganaSentence(sentence: Sentence) {
        sentence.japaneseWithReadings.forEach {
            if (it.reading == null)
                +it.word
            else
                ruby {
                    +it.word
                    rt { +it.reading }
                }
        }
    }

    private fun BODY.definitions(entry: EntryElement, language: List<Language>) {
        section(Stylesheet.DEFINITIONS) {
            h3(Stylesheet.SECTION_HEADING) { +"Definitions" }

            entry.senseElement.filter { sense ->
                sense.gloss?.any { language.contains(it.language) } ?: false
            }.forEachIndexed { index, sense ->
                article(Stylesheet.TRANSLATION_LINE) {
                    p(Stylesheet.ENTRY_NUMBER) { +(index + 1).toString() }

                    div(Stylesheet.TRANSLATION_BLOCK) {
                        sense.gloss?.filter { gloss ->
                            language.contains(gloss.language)
                        }?.forEachIndexed { index, gloss ->
                            p(Stylesheet.TRANSLATION) {
                                if (sense.gloss.isIndexOfFinalElement(index))
                                    +gloss.element
                                else
                                    +"${gloss.element},"
                            }
                        }
                    }

                    div(Stylesheet.BADGE_BOX) {
                        sense.partOfSpeech?.forEach {
                            div(Stylesheet.BADGE) { +it.code }
                        }
                    }
                }
            }
        }
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

    private fun EntryElement.requiresSubHeader() =
        kanaReadings().size > 1 || (kanjiReadings() ?: emptyList()).size > 1

    fun japaneseEntry(entry: EntryElement, sentences: List<Sentence>?) =
        document.create.html {
            body {
                title(entry)
                if (entry.requiresSubHeader()) {
                    section(Stylesheet.SUB_HEADER) {
                        kanaReadings(entry)
                        kanjiReadings(entry)
                    }
                }
                sentences(sentences)
                definitions(entry, language)
            }
        }
}
