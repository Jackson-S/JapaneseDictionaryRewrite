package jmdict.datatypes

data class SourceLanguageElement(
    val language: String,

    val information: String?,

    /**
     * The ls_type attribute indicates whether the lsource element
     * fully or partially describes the source word or phrase of the
     * loanword. If absent, it will have the implied value of "full".
     * Otherwise it will contain "part".
     */
    val partial: Boolean,

    /**
     * The ls_wasei attribute indicates that the Japanese word
     * has been constructed from words in the source language, and
     * not from an actual phrase in that language. Most commonly used to
     * indicate "waseieigo".
     */
    val waseiEigo: Boolean
)
