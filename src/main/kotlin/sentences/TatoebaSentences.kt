package sentences

import loader.Loader
import sentences.datatypes.Sentence
import sentences.datatypes.Word

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
        private val COMMENT_REGEX = "#.*".toRegex()
        private val VERIFIED_MARKER_REGEX = VERIFIED_MARKER.toRegex()
        private const val HIRAGANA_FORM_PARENTHESIS = "()"
    }

    private val sentenceList: List<Sentence>
    private val verifiedWordMapping: MutableMap<String, MutableList<Sentence>> = mutableMapOf()

    init {
        val processedLines = loader.contentsAsText().split('\n').filter { !it.startsWith(COMMENT_PREFIX) }

        val sentenceLines = processedLines.filter { it.startsWith(SENTENCE_PAIR_PREFIX) }.map {
            it.deleteFirst(SENTENCE_PAIR_PREFIX)
            it.delete(COMMENT_REGEX)
        }

        val japaneseWordLines = processedLines.filter { it.startsWith(JAPANESE_WORD_LIST_PREFIX) }.map {
            it.deleteFirst(JAPANESE_WORD_LIST_PREFIX)
        }

        // Create the sentence list
        sentenceList = sentenceLines.zip(japaneseWordLines).map { (sentences, japaneseWithInfo) ->
            // Get the raw japanese and english sentences
            val (japaneseSentence, englishSentence) = sentences.split(SENTENCE_PAIR_DELIMITER)
            val verifiedWords = japaneseWithInfo.verifiedWords()

            val sentence = Sentence(
                japanese = japaneseSentence,
                japaneseWithReadings = japaneseWithInfo.toFuriganaSentence(),
                english = englishSentence,
                verifiedWords = verifiedWords
            )

            verifiedWords.forEach { word ->
                verifiedWordMapping.addEntry(word, sentence)
            }

            sentence
        }
    }

    /**
     * Takes the japanese breakdown and returns a list of Word objects with readings where provided
     */
    private fun String.toFuriganaSentence() =
        split(JAPANESE_WORD_LIST_DELIMITER).map { wordWithInfo ->
            val hiraganaForm = wordWithInfo.hiraganaForm()
            val originalForm = wordWithInfo.originalForm()

            Word(originalForm, hiraganaForm)
        }

    /**
     * Given a B line return the plain form of all verified words
     */
    private fun String.verifiedWords() =
        split(JAPANESE_WORD_LIST_DELIMITER).filter { wordWithInfo ->
            wordWithInfo.contains(VERIFIED_MARKER_REGEX)
        }.map { wordWithInfo ->
            wordWithInfo.originalForm()
        }

    private fun String.hiraganaForm() = HIRAGANA_FORM_REGEX.find(this)?.value
        ?.trim(*HIRAGANA_FORM_PARENTHESIS.toCharArray())

    private fun String.originalForm() = this
        .delete(SENSE_MARKER_REGEX, SENTENCE_FORM_REGEX, HIRAGANA_FORM_REGEX, VERIFIED_MARKER_REGEX)
        .deleteFirst(JAPANESE_WORD_LIST_PREFIX)

    private fun MutableMap<String, MutableList<Sentence>>.addEntry(key: String, value: Sentence) =
        getOrPut(key) { mutableListOf() }.add(value)

    private fun String.delete(vararg regex: Regex): String {
        var result = this
        regex.forEach {
            result = result.replace(it, "")
        }
        return result
    }

    private fun String.deleteFirst(string: String) = replaceFirst(string, "")

    fun sentencesForWord(japaneseWord: String) =
        verifiedWordMapping[japaneseWord]?.toList()
}
