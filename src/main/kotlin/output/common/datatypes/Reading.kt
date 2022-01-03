package output.common.datatypes

data class Reading(
    /**
     * The text of the reading
     */
    val value: String,

    /**
     * Any extra information pertaining to this reading, e.g. "usually kana"
     */
    val information: List<String>?
)
