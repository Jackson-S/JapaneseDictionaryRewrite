package sentences

import loader.Loader
import sentences.datatypes.Sentence
import java.nio.charset.CharsetDecoder
import java.nio.charset.CharsetEncoder

class TatoebaSentences(
    loader: Loader
) {
    companion object {
        private const val SENTENCE_PAIR_PREFIX = "A: "
        private const val SENTENCE_PAIR_DELIMITER = "\t"
        private const val SENTENCE_ID_PREFIX = "#ID="
        private const val JAPANESE_WORD_LIST_PREFIX = "B: "
        private const val JAPANESE_WORD_LIST_DELIMITER = " "
        private const val COMMENT_PREFIX = "#"
        private const val VERIFIED_MARKER = "~"
        private val SENSE_MARKER_REGEX = "\\[.*]".toRegex()
        private val SENTENCE_FORM_REGEX = "\\{.*}".toRegex()
        private val HIRAGANA_FORM_REGEX = "\\(.*\\)".toRegex()
        private val HIRAGANA_FORM_PARENTHESIS = "()"
    }

    private val sentenceList: List<Sentence>
    private val wordMap: MutableMap<String, MutableList<Sentence>>

    init {
        val processedLines = loader.contentsAsText().split('\n').filter { !it.startsWith(COMMENT_PREFIX) }

        val sentenceLines = processedLines.filter { it.startsWith(SENTENCE_PAIR_PREFIX) }
        val japaneseWordLines = processedLines.filter { it.startsWith(JAPANESE_WORD_LIST_PREFIX) }

        // Create the sentence list
        sentenceList = sentenceLines.zip(japaneseWordLines).map { (sentences, japaneseWords) ->
            // Remove the sentence id prefix at the end of each sentence pair
            val sentencesCleaned = sentences.split(SENTENCE_ID_PREFIX).first()
            val japaneseSentence = sentencesCleaned.split(SENTENCE_PAIR_DELIMITER)[0]
            val englishSentence = sentencesCleaned.split(SENTENCE_PAIR_DELIMITER)[1]
            val wordlist = japaneseWords.split(JAPANESE_WORD_LIST_DELIMITER).map {
                it.replace(SENSE_MARKER_REGEX, "")
                    .replace(SENTENCE_FORM_REGEX, "")
                    .replace(HIRAGANA_FORM_REGEX, "")
            }
            val irregularWords = japaneseWords.split(' ').filter {
                it.contains(HIRAGANA_FORM_REGEX)
            }.associate {
                val word = it.replace(SENSE_MARKER_REGEX, "")
                    .replace(SENTENCE_FORM_REGEX, "")
                    .replace(HIRAGANA_FORM_REGEX, "")
                val reading = HIRAGANA_FORM_REGEX.find(it)!!.value
                    .trim(HIRAGANA_FORM_PARENTHESIS[0], HIRAGANA_FORM_PARENTHESIS[1])
                word to reading
            }
            Sentence(
                japanese = japaneseSentence,
                english = englishSentence,
                verifiedWords = wordlist,
                japaneseIrregularWords = irregularWords
            )
        }

        // Create a mapping of words to sentences containing them
        wordMap = mutableMapOf()
        sentenceList.forEach { sentence ->
            sentence.verifiedWords.forEach {
                if (wordMap.contains(it)) {
                    if (!wordMap[it]!!.contains(sentence)) {
                        wordMap[it]!!.add(sentence)
                    }
                } else {
                    wordMap[it] = mutableListOf(sentence)
                }
            }
        }
    }

    fun sentences(japaneseWord: String) =
        wordMap[japaneseWord]?.toList()
}