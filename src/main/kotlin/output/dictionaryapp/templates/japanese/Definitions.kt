package output.dictionaryapp.templates.japanese

import common.Language
import jmdict.datatypes.EntryElement
import jmdict.datatypes.SenseElement
import jmdict.enums.BasePartOfSpeechEnum
import jmdict.enums.PartOfSpeechEnum
import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.SPAN
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import kotlinx.html.section
import kotlinx.html.span
import kotlinx.html.title
import output.dictionaryapp.EnumMapping
import output.dictionaryapp.templates.HtmlClass

object Definitions {
    private const val BULLET = "•"

    fun BODY.definitions(entry: EntryElement, languages: List<Language>) {
        section(HtmlClass.DEFINITIONS) {
            h3 { +"Definitions" }

            val senses = relevantSenses(languages, entry)

            ol {
                senses.forEach { sense ->
                    li {
                        span(HtmlClass.TRANSLATION_LINE) {
                            div(HtmlClass.TRANSLATION_BLOCK) {
                                partOfSpeechAbbreviations(sense.partOfSpeech)?.let {
                                    p(HtmlClass.PART_OF_SPEECH_MARKER) { +it }
                                }
                                sense.fieldsPrefix()?.let { p(HtmlClass.FIELD_INFORMATION) { +it } }
                                glosses(sense, languages)
                            }

                            information(sense)
                        }
                    }
                }
            }
        }
    }

    private fun partOfSpeechAbbreviations(partOfSpeech: List<PartOfSpeechEnum>?) =
        partOfSpeech?.mapNotNull {
            it.abbreviation()
        }?.distinctBy {
            it.first
        }?.joinToString(" ") {
            it.first
        }

    private fun relevantSenses(languages: List<Language>, entry: EntryElement) =
        entry.senseElement.filter { sense -> sense.gloss?.any { languages.contains(it.language) } ?: false }

    private fun DIV.badges(sense: SenseElement) {
        div(HtmlClass.BADGE_BOX) {
            sense.misc?.forEach { misc ->
                div("${HtmlClass.BADGE} ${HtmlClass.BADGE_MISC}") {
                    attributes["apple_mouseover_disable"] = "1"
                    title = EnumMapping.map(misc).second
                    +EnumMapping.map(misc).first
                }
            }
        }
    }

    private fun SPAN.information(sense: SenseElement) {
        if (!sense.information.isNullOrEmpty()) {
            div(HtmlClass.SENSE_INFORMATION) {
                sense.information.forEach { information ->
                    p {
                        attributes["apple_mouseover_disable"] = "1"
                        +"$BULLET $information"
                    }
                }
            }
        }
    }

    private fun SenseElement.fieldsPrefix() =
        when (field.size) {
            0 -> null
            1 -> "In ${field.first().description}"
            2 -> "In ${field.joinToString(" and ") { it.description }}"
            else -> "In ${field.subList(0, field.size - 1).joinToString(", ") { it.description }} and ${field.last().description}"
        }

    private fun DIV.glosses(sense: SenseElement, languages: List<Language>) {
        sense.gloss?.filter {
            languages.contains(it.language)
        }?.forEach { gloss ->
            p { +gloss.element }
        }
    }

    private fun PartOfSpeechEnum.abbreviation() = when (basePartOfSpeech) {
        BasePartOfSpeechEnum.ADJECTIVE -> Pair("adj.", description)
        BasePartOfSpeechEnum.ADVERB -> Pair("adv.", description)
        BasePartOfSpeechEnum.NOUN -> Pair("n.", description)
        BasePartOfSpeechEnum.PARTICLE -> Pair("part.", description)
        BasePartOfSpeechEnum.VERB -> Pair("v.", description)
        null -> null
    }
}
