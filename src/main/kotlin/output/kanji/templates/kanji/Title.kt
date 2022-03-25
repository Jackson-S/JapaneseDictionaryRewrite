package output.kanji.templates.kanji

import kanjidic.datatypes.CharacterElement
import kotlinx.html.BODY
import kotlinx.html.h1
import kotlinx.html.header

internal object Title {
    fun BODY.title(entry: CharacterElement) {
        header {
            h1 { +entry.literal }
        }
    }
}
