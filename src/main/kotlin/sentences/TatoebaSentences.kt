package sentences

import loader.Loader
import sentences.datatypes.Sentence

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
        private const val HIRAGANA_FORM_PARENTHESIS = "()"
    }

    private val sentenceList: List<Sentence>
    private val wordMap: MutableMap<String, MutableList<Sentence>>

    init {
        val processedLines = loader.contentsAsText().split('\n').filter { !it.startsWith(COMMENT_PREFIX) }

        val sentenceLines = processedLines.filter { it.startsWith(SENTENCE_PAIR_PREFIX) }
        val japaneseWordLines = processedLines.filter { it.startsWith(JAPANESE_WORD_LIST_PREFIX) }

        // Create the sentence list
        sentenceList = sentenceLines.zip(japaneseWordLines).map { (sentences, japaneseWords) ->
            // Get the raw japanese and english sentences
            val (japaneseSentence, englishSentence) = sentences
                .split(SENTENCE_ID_PREFIX)
                .first()
                .deleteFirst(SENTENCE_PAIR_PREFIX)
                .split(SENTENCE_PAIR_DELIMITER)

            // Break up the list of japanese words in the sentence
            val wordlist = japaneseWords.split(JAPANESE_WORD_LIST_DELIMITER).map { word ->
                word.delete(SENSE_MARKER_REGEX, SENTENCE_FORM_REGEX, HIRAGANA_FORM_REGEX)
            }

            // Get a list of irregular readings or words in the sentence
            val irregularWords = japaneseWords.split(' ').filter {
                it.contains(HIRAGANA_FORM_REGEX)
            }.associate {
                val word = it.replace(SENSE_MARKER_REGEX, "")
                    .delete(SENTENCE_FORM_REGEX, HIRAGANA_FORM_REGEX)
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

    fun sentencesForWord(japaneseWord: String) =
        wordMap[japaneseWord]?.toList()

    private fun String.delete(vararg regex: Regex): String {
        var result = this
        regex.forEach {
            result = result.replace(it, "")
        }
        return result
    }

    private fun String.deleteFirst(string: String) = replaceFirst(string, "")
}
