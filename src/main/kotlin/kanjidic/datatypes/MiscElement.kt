package kanjidic.datatypes

// grade?, stroke_count+, variant*, freq?, rad_name*,jlpt?

data class MiscElement(
    /**
     * The kanji grade level. 1 through 6 indicates a Kyouiku kanji
     * and the grade in which the kanji is taught in Japanese schools.
     * 8 indicates it is one of the remaining Jouyou Kanji to be learned
     * in junior high school. 9 indicates it is a Jinmeiyou (for use
     * in names) kanji which in addition  to the Jouyou kanji are approved
     * for use in family name registers and other official documents. 10
     * also indicates a Jinmeiyou kanji which is a variant of a
     * Jouyou kanji. \[G\]
     */
    val grade: Int?,

    /**
     * The stroke count of the kanji, including the radical. If more than
     * one, the first is considered the accepted count, while subsequent ones
     * are common miscounts. (See Appendix E. of the KANJIDIC documentation
     * for some of the rules applied when counting strokes in some of the
     * radicals.) \[S\]
     */
    val strokeCount: List<Int>,

    /**
     * Either a cross-reference code to another kanji, usually regarded as a
     * variant, or an alternative indexing code for the current kanji.
     * The type of variant is given in the var_type attribute.
     */
    val variant: List<VariantElement>?,

    /**
     * A frequency-of-use ranking. The 2,500 most-used characters have a
     * ranking; those characters that lack this field are not ranked. The
     * frequency is a number from 1 to 2,500 that expresses the relative
     * frequency of occurrence of a character in modern Japanese. This is
     * based on a survey in newspapers, so it is biassed towards kanji
     * used in newspaper articles. The discrimination between the less
     * frequently used kanji is not strong. (Actually there are 2,501
     * kanji ranked as there was a tie.)
     */
    val frequencyOfUse: Int?,

    /**
     * When the kanji is itself a radical and has a name, this element
     * contains the name (in hiragana.)
     */
    val radicalName: List<String>?,

    /**
     * The (former) Japanese common.Language Proficiency test level for this kanji.
     * Values range from 1 (most advanced) to 4 (most elementary). This field
     * does not appear for kanji that were not required for any JLPT level.
     * Note that the JLPT test levels changed in 2010, with a new 5-level
     * system (N1 to N5) being introduced. No official kanji lists are
     * available for the new levels. The new levels are regarded as
     * being similar to the old levels except that the old level 2 is
     * now divided between N2 and N3.
     */
    val jlptLevel: Int?
)
