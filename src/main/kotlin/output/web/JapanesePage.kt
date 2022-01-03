package output.web

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.stream.appendHTML
import kotlinx.html.stream.createHTML
import output.common.datatypes.JapaneseEntry
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import kotlin.text.StringBuilder

class JapanesePage(
    entry: JapaneseEntry
) {
    val page: String

    init {
        page = buildString {
            appendHTML(true).html {
                head {
                    script { unsafe { +Constants.SCRIPT } }
                }
                body {
                    section { id = "header"
                        h1 { id = "page_title"
                            +entry.title
                        }
                        if (entry.kanaReadings.size == 1) {
                            h1 {
                                id = "primary_reading"
                                +"【${entry.kanaReadings.first().value}】"
                            }
                        }
                    }

                    section { id = "sub_header"
                        if (entry.kanaReadings.size > 1 || (entry.kanjiReadings?.size ?: 0) > 1) {
                            section { id = "readings"
                                // Kana readings
                                if (entry.kanaReadings.size > 1) { // Otherwise will be displayed at top
                                    section {
                                        id = "onyomi"
                                        h3(classes = "section_heading") {
                                            attributes["apple_mouseover_disable"] = "1"
                                            +"Readings"
                                        }

                                        entry.kanaReadings.forEach { reading ->
                                            div(classes = "reading") {
                                                p { +reading.value }
                                                reading.information?.forEach {
                                                    div(classes = "badge") { +it }
                                                }
                                            }
                                        }
                                    }
                                }

                                // Alternate kanji
                                if ((entry.kanjiReadings?.size ?: 0) > 1) {
                                    section { id = "alt_kanji"
                                        h3(classes="section_heading") {
                                            attributes["apple_mouseover_disable"] = "1"
                                            +"Alternate Kanji"
                                        }

                                        entry.kanjiReadings?.subList(1, entry.kanjiReadings.size)?.forEach { reading ->
                                            div(classes="reading") {
                                                p { +reading.value }
                                                reading.information?.forEach { info ->
                                                    div(classes="badge") { +info }
                                                }
                                            }
                                        }
                                    }
                                }

                                // Contained kanji
                                if (!entry.containedKanji.isNullOrEmpty()) {
                                    section { id = "containing_kanji"
                                        h3(classes="section_heading") {
                                            attributes["apple_mouseover_disable"] = "1"
                                            +"Kanji in this Term"
                                        }

                                        entry.containedKanji.forEach { kanji ->
                                            div(classes="reading") {
                                                a { href="x-dictionary:r:jp_kanji_$kanji"
                                                    +kanji
                                                }
                                                p {
                                                    +kanji
                                                }
                                            }
                                        }
                                    }
                                }

                                if (!entry.sentences.isNullOrEmpty()) {
                                    details { id="sentences"
                                        summary(classes="section_heading") {
                                            attributes["apple_mouseover_disable"] = "1"
                                            +"Example Sentences"
                                        }
                                        entry.sentences.forEach { sentence ->
                                            article(classes="sentence") {
                                                p { sentence.japanese }
                                                p { sentence.english }
                                            }
                                        }
                                    }
                                }

                                section {
                                    id = "definitions"
                                    h3(classes="section_heading") {
                                        attributes["apple_mouseover_disable"] = "1"
                                        +"Definitions"
                                    }
                                    entry.definitions.forEachIndexed { index, definition ->
                                        article {
                                            p(classes = "number") {
                                                attributes["apple_mouseover_disable"] = "1"
                                                +(index + 1).toString()
                                            }
                                            div(classes="translation_line") {
                                                definition.value.forEachIndexed { sub_index, value ->
                                                    p(classes = "translation") {
                                                        if (definition.value.size == sub_index+1) {
                                                            +value
                                                        } else {
                                                            +"$value,"
                                                        }
                                                    }
                                                }
                                            }

                                            div(classes="badges") {
                                                definition.partOfSpeech.forEach {
                                                    div(classes = "badge") { +it }
                                                }
                                            }
                                            if (definition.information.isNotEmpty()) {
                                                div(classes="translation_info") {
                                                    p { +"↳" }
                                                    definition.information.forEach {
                                                        p { +it }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}