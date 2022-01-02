package sentences.datatypes

data class SentencePair(
    val japanese: List<MecabOutput>,
    val english: String
)
