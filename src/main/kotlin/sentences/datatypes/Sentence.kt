package sentences.datatypes

data class Sentence(
    val japanese: String,
    val english: String,
    val verifiedWords: List<String>,
    /**
     * Any irregular readings, the first is the word as it appears, and the second is the reading
     */
    val japaneseIrregularWords: Map<String, String>,
)
