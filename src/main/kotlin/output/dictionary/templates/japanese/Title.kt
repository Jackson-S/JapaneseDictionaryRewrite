package output.dictionary.templates.japanese

import jmdict.datatypes.EntryElement
import kotlinx.html.BODY
import kotlinx.html.h1
import kotlinx.html.header
import output.dictionary.templates.HtmlClass

internal object Title {
    fun BODY.title(entry: EntryElement) {
        header {
            h1 { +entry.headWord }
            entry.headReading?.let { h1(HtmlClass.HEADER_READING) { +it } }
        }
    }
}
