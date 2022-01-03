package output.common.datatypes

data class Definition(
    /**
     * A definition or group of definitions
     */
    val value: List<String>,

    /**
     * The parts-of-speech that this definition or translation pertains to (e.g. verb, adjective etc)
     */
    val partOfSpeech: List<String>,

    /**
     * Any further information pertaining to this definition or translation
     */
    val information: List<String>
)
