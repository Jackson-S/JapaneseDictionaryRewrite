package output.kanji.templates.kanji

import jmdict.datatypes.EntryElement
import jmdict.datatypes.KanjiElement
import kanjidic.datatypes.CharacterElement
import kanjidic.enums.VariantTypeEnum
import kotlinx.html.BODY
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.header
import output.dictionary.templates.HtmlClass

internal object Title {
    fun BODY.title(entry: CharacterElement) {
        header {
            h1 { +entry.literal }
        }
    }
}
