package sentences.datatypes

data class MecabOutput(
    val originalForm: String,
    val partOfSpeech: String,
    val subPartOfSpeech: String?,
    val subSubPartOfSpeech: String?,
    val subSubSubPartOfSpeech: String?,
    val subSubSubSubPartOfSpeech: String?,
    val conjugatedForm: String?,
    val inflection: String?,
    val reading: String?,
    val pronunciation: String?
) {
    companion object {
        fun parse(rawOutput: String): MecabOutput {
            val originalForm = rawOutput.split("\t")[0]
            val otherVariables = rawOutput.split("\t")[1]
                .split(",")
                .map { if (it == "*") null else it }

            return MecabOutput(
                originalForm = originalForm,
                partOfSpeech = otherVariables[0]!!,
                subPartOfSpeech = otherVariables.getOrNull(0),
                subSubPartOfSpeech = otherVariables.getOrNull(0),
                subSubSubPartOfSpeech = otherVariables.getOrNull(0),
                subSubSubSubPartOfSpeech = otherVariables.getOrNull(0),
                conjugatedForm = otherVariables.getOrNull(0),
                inflection = otherVariables.getOrNull(0),
                reading = otherVariables.getOrNull(0),
                pronunciation = otherVariables.getOrNull(0),
            )
        }
    }
}
