package kanjidic.datatypes

data class ReadingMeaningElement(
    /**
     * The reading element contains the reading or pronunciation
     * of the kanji.
     */
    val reading: List<ReadingElement>?,

    val meaning: List<MeaningElement>?,
)
