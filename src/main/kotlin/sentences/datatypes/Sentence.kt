package sentences.datatypes

data class Sentence(
    val japanese: String,
    val japaneseWithReadings: List<Word>,
    val english: String,
    val verifiedWords: List<String>
)
