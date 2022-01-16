package output.kanji.templates.kanji

import common.Language
import kanjidic.datatypes.CharacterElement
import kotlinx.html.body
import kotlinx.html.dom.create
import kotlinx.html.html
import org.w3c.dom.Document
import org.w3c.dom.Element
import output.common.Page
import output.kanji.templates.kanji.Definitions.definitions
import output.kanji.templates.kanji.SubHeader.subheader
import output.kanji.templates.kanji.Title.title

class KanjiPage(
    document: Document,
    languages: List<Language>,
    entry: CharacterElement
) : Page {
    override val page: Element = document.create.html {
        body {
            title(entry)
            entry.readingMeaning?.forEach { readingMeaning ->
                subheader(readingMeaning)
                definitions(readingMeaning, languages)
            }
        }
    }
}