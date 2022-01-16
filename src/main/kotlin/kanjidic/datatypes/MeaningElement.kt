package kanjidic.datatypes

import common.Language

data class MeaningElement(
    /**
     * The meaning associated with the kanji.
     */
    val element: String,

    /**
     * The m_lang attribute defines the target language of the meaning. It
     * will be coded using the two-letter language code from the ISO 639-1
     * standard. When absent, the value "en" (i.e. English) is implied. [{}]
     */
    val language: Language
)
