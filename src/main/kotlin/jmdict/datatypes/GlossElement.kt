package jmdict.datatypes

import Language

data class GlossElement(
    val element: String,

    /**
     * The xml:lang attribute defines the target language of the
     * gloss. It will be coded using the three-letter language code from
     * the ISO 639 standard. When absent, the value "eng" (i.e. English)
     * is the default value.
     */
    val language: Language,

    /**
     * The g_gend attribute defines the gender of the gloss (typically
     * a noun in the target language. When absent, the gender is either
     * not relevant or has yet to be provided.
     */
    val gender: String?,

    /**
     * The g_type attribute specifies that the gloss is of a particular
     * type, e.g. "lit" (literal), "fig" (figurative), "expl" (explanation).
     */
    val type: String?,

    /**
     * These elements highlight particular target-language words which
     * are strongly associated with the Japanese word. The purpose is to
     * establish a set of target-language words which can effectively be
     * used as head-words in a reverse target-language/Japanese relationship.
     */
    val priority: List<String>?
)
