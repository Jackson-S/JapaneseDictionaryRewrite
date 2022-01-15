package output.dictionaryapp.templates.japanese

import common.Language
import jmdict.datatypes.EntryElement
import kotlinx.html.BODY
import kotlinx.html.P
import kotlinx.html.article
import kotlinx.html.details
import kotlinx.html.p
import kotlinx.html.rp
import kotlinx.html.rt
import kotlinx.html.ruby
import kotlinx.html.summary
import output.dictionaryapp.templates.HtmlClass
import sentences.datatypes.Sentence

internal object Sentences {
    fun BODY.sentences(entry: EntryElement, sentences: List<Sentence>?, limit: Int = 3) {
        if (!isRequired(Configuration.LANGUAGE, sentences, limit))
            return

        details {
            summary(HtmlClass.MOUSEOVER_DISABLE) { +"Example Sentences" }

            sentences!!.first(limit).forEach { sentence ->
                article(HtmlClass.SENTENCE) {
                    p { furiganaSentence(sentence) }
                    p { +sentence.english }
                }
            }
        }
    }

    private fun P.furiganaSentence(sentence: Sentence) {
        sentence.japaneseWithReadings.forEach {
            if (it.reading == null)
                +it.word
            else
                ruby {
                    +it.word
                    rt { +it.reading }
                    rp { +it.reading }
                }
        }
    }

    private fun isRequired(languages: List<Language>, sentences: List<Sentence>?, limit: Int) =
        languages.contains(Language.ENGLISH) && !sentences.isNullOrEmpty() && limit > 0

    private fun <T> List<T>.first(limit: Int) = if (size >= limit) subList(0, limit - 1) else this
}