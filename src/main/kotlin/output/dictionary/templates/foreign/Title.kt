package output.dictionary.templates.foreign

import kotlinx.html.BODY
import kotlinx.html.h1
import kotlinx.html.header

internal object Title {
    fun BODY.title(word: String) {
        header { h1 { +word } }
    }
}
