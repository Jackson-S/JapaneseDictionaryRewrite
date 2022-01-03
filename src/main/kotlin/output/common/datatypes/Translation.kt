package output.common.datatypes

data class Translation(
    val original: String,

    /**
     * Any extra descriptions of where this translation is applicable
     */
    val contextualWords: List<String>,

    /**
     * The parts-of-speech that this translation pertains to
     */
    val partOfSpeech: List<String>
)