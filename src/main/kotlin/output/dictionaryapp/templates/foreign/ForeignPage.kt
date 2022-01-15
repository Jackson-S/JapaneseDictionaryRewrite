package output.dictionaryapp.templates.foreign

import jmdict.datatypes.EntryElement
import kotlinx.html.body
import kotlinx.html.dom.create
import kotlinx.html.html
import org.w3c.dom.Document
import output.dictionaryapp.templates.Page
import output.dictionaryapp.templates.foreign.Definitions.definitions
import output.dictionaryapp.templates.foreign.Title.title

class ForeignPage(
    document: Document,
    word: String,
    entries: List<EntryElement>
) : Page {
    override val page = document.create.html {
        body {
            title(word)
            definitions(word, entries)
        }
    }
}
