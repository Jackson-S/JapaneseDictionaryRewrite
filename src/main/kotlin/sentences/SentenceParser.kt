package sentences

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import loader.Loader
import sentences.datatypes.MecabOutput
import sentences.datatypes.SentencePair

class SentenceParser(
    sentenceLoader: Loader,
    indexLoader: Loader,
    languages: List<String>
) {
    companion object {
        private const val VERIFIED_INDICATOR = "~"
        private const val JAPANESE_LANGUAGE_CODE = "jpn"

        private object SentenceCSVColumns {
            const val INDEX_COLUMN = 0
            const val LANGUAGE_COLUMN = 1
            const val SENTENCE_COLUMN = 2
        }

        private object IndexCSVColumns {
            const val JAPANESE_INDEX = 0
            const val ENGLISH_INDEX = 1
            const val PARAMETERS = 2
        }
    }

    private val tagger = Mecab()

    private val sentences: List<SentencePair>

    init {
        val tsvReader = csvReader {
            escapeChar = '\\'
            delimiter = '\t'
            skipEmptyLine = true
        }

        val sentences = tsvReader.readAll(sentenceLoader.contents(Loader.Format.TEXT) as String)
        val indices = tsvReader.readAll(indexLoader.contents(Loader.Format.TEXT) as String)

        val languageList = listOf(languages, listOf(JAPANESE_LANGUAGE_CODE)).flatten()

        val sentencesInLanguage = sentences.filter {
            val sentenceLanguage = it[SentenceCSVColumns.LANGUAGE_COLUMN]
            languageList.contains(sentenceLanguage)
        }.associateBy { it[SentenceCSVColumns.INDEX_COLUMN] }

        val verifiedWords = indices.filter {
            // Only get verified words
            it[IndexCSVColumns.PARAMETERS].contains(VERIFIED_INDICATOR) &&
                // Only get words with a translation
                sentencesInLanguage.keys.contains(it[IndexCSVColumns.ENGLISH_INDEX]) &&
                sentencesInLanguage.keys.contains(it[IndexCSVColumns.JAPANESE_INDEX])
        }

        this.sentences = verifiedWords.map {
            val japaneseSentence =
                sentencesInLanguage[it[IndexCSVColumns.JAPANESE_INDEX]]!![SentenceCSVColumns.SENTENCE_COLUMN]

            SentencePair(
                japanese = tagger.tokenizeSentence(japaneseSentence).lines().map { line -> MecabOutput.parse(line) },
                english = sentencesInLanguage[it[IndexCSVColumns.ENGLISH_INDEX]]!![SentenceCSVColumns.SENTENCE_COLUMN],
            )
        }
    }
}
