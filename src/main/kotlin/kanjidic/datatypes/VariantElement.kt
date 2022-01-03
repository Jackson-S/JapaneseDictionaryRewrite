package kanjidic.datatypes

import kanjidic.enums.VariantTypeEnum

/**
 * Either a cross-reference code to another kanji, usually regarded as a
 * variant, or an alternative indexing code for the current kanji.
 * The type of variant is given in the var_type attribute.
 */
data class VariantElement(
    val index: String?,

    /**
     * 	The var_type attribute indicates the type of variant code. The current
     * values are:
     * jis208 - in JIS X 0208 - kuten coding
     * jis212 - in JIS X 0212 - kuten coding
     * jis213 - in JIS X 0213 - kuten coding
     * (most of the above relate to "shinjitai/kyuujitai"
     * alternative character glyphs)
     * deroo - De Roo number - numeric
     * njecd - Halpern NJECD index number - numeric
     * s_h - The Kanji Dictionary (Spahn & Hadamitzky) - descriptor
     * nelson_c - "Classic" Nelson - numeric
     * oneill - Japanese Names (O'Neill) - numeric
     * ucs - Unicode codepoint- hex
     */
    val type: VariantTypeEnum
)
