package output.dictionary.templates.foreign

import common.Language
import jmdict.datatypes.EntryElement
import jmdict.datatypes.SenseElement
import kotlinx.html.BODY
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import kotlinx.html.section
import kotlinx.html.span
import output.dictionary.templates.HtmlClass

internal object Definitions {
    private data class Translation(
        val translation: String,
        val contextWords: List<String>
    )

    fun BODY.definitions(word: String, language: Language, entries: List<EntryElement>) {
        val filteredSenses = filterSenses(word, language, entries)
        val translationList = createTranslationObjects(filteredSenses)
        val groupedTranslationList = translationList.groupBy { it.contextWords }.map { it.value }

        section(HtmlClass.DEFINITIONS) {
            h3(HtmlClass.SECTION_HEADING) { +"Definitions" }

            ol {
                groupedTranslationList.forEach { translations ->
                    li {
                        span(HtmlClass.TRANSLATION_LINE) {
                            div(HtmlClass.TRANSLATION_BLOCK) {
                                div(HtmlClass.TRANSLATION) {
                                    translations.forEach { p { +it.translation } }
                                }
                                if (translations.first().contextWords.size > 1) {
                                    div(HtmlClass.CONTEXT) {
                                        translations.first().contextWords.forEach { contextWord -> p { +contextWord } }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun filterSenses(word: String, language: Language, entries: List<EntryElement>) =
        entries.map { entry ->
            val senses = entry.senseElement.filter { sense ->
                sense.gloss?.any {
                    it.element == word && it.language == language
                } ?: false
            }

            Pair(entry, senses)
        }

    private fun createTranslationObjects(filteredSenses: List<Pair<EntryElement, List<SenseElement>>>) =
        filteredSenses.map { (entry, senses) ->
            val glosses = senses.flatMap { sense ->
                sense.gloss?.map { gloss -> gloss.element } ?: emptyList()
            }.distinct()
            Translation(entry.headWord, glosses)
        }.distinctBy { it.translation + it.contextWords.joinToString() }
}
