package kanjidic.datatypes

data class HeaderElement(
    /**
     * This field denotes the version of kanjidic2 structure, as more
     * than one version may exist.
     */
    val fileVersion: String,

    /**
     * The version of the file, in the format YYYY-NN, where NN will be
     * a number starting with 01 for the first version released in a
     * calendar year, then increasing for each version in that year.
     */
    val databaseVersion: String,

    /**
     * The date the file was created in international format (YYYY-MM-DD).
     */
    val dateOfCreation: String
)
