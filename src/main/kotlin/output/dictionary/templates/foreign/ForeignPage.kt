package output.dictionary.templates.foreign

import common.Language
import jmdict.datatypes.EntryElement
import kotlinx.html.body
import kotlinx.html.dom.create
import kotlinx.html.html
import org.w3c.dom.Document
import output.common.Page
import output.dictionary.templates.foreign.Definitions.definitions
import output.dictionary.templates.foreign.Title.title

class ForeignPage(
    document: Document,
    word: String,
    language: Language,
    entries: List<EntryElement>
) : Page {
    override val page = document.create.html {
        body {
            title(word)
            definitions(word, language, entries)
        }
    }
}
