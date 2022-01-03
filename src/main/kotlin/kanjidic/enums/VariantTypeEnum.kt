package kanjidic.enums

/**
 * The var_type attribute indicates the type of variant code. The current
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
enum class VariantTypeEnum(val code: String) {
    JIS208("jis208"),
    JIS212("jis212"),
    JIS213("jis213"),
    DE_ROO("deroo"),
    NJECD("njecd"),
    SPAHN_HADAMITZKY("s_h"),
    NELSON_CLASSIC("nelson_c"),
    ONEILL("oneill"),
    UNICODE("ucs");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
