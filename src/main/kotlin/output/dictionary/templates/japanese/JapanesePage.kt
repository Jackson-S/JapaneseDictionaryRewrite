package output.dictionary.templates.japanese

import common.Language
import jmdict.datatypes.EntryElement
import kotlinx.html.body
import kotlinx.html.dom.create
import kotlinx.html.html
import org.w3c.dom.Document
import org.w3c.dom.Element
import output.common.Page
import output.dictionary.templates.japanese.Definitions.definitions
import output.dictionary.templates.japanese.Sentences.sentences
import output.dictionary.templates.japanese.SubHeader.subheader
import output.dictionary.templates.japanese.Title.title
import sentences.datatypes.Sentence

class JapanesePage(
    document: Document,
    languages: List<Language>,
    entry: EntryElement,
    sentences: List<Sentence>?
) : Page {

    override val page: Element = document.create.html {
        body {
            title(entry)
            subheader(entry)
            sentences(sentences)
            definitions(entry, languages)
        }
    }
}
