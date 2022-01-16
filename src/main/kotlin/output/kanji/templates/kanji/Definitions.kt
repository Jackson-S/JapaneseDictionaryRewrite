package output.kanji.templates.kanji

import common.Language
import kanjidic.datatypes.ReadingMeaningElement
import kanjidic.enums.ReadingTypeEnum
import kotlinx.html.BODY
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import kotlinx.html.section
import kotlinx.html.span
import output.dictionary.templates.HtmlClass

object Definitions {
    fun BODY.definitions(readingMeaning: ReadingMeaningElement, languages: List<Language>) {
        section(HtmlClass.DEFINITIONS) {
            h3 { +"Definitions" }
            ol {
                readingMeaning.meaning?.filter {
                    languages.contains(it.language)
                }?.forEach { meaning ->
                    li {
                        span(HtmlClass.TRANSLATION_LINE) {
                            div(HtmlClass.TRANSLATION_BLOCK) {
                                p { +meaning.element }
                            }
                        }
                    }
                }
            }
        }
    }
}
