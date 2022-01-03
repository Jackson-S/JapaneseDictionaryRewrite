package kanjidic.datatypes

import kanjidic.enums.CodepointCharsetEnum

data class CodepointElement(
    /**
     * The cp_value contains the codepoint of the character in a particular
     * standard. The standard will be identified in the cp_type attribute.
     */
    val value: String,

    /**
     * 	The cp_type attribute states the coding standard applying to the
     * element. The values assigned so far are:
     * jis208 - JIS X 0208-1997 - kuten coding (nn-nn)
     * jis212 - JIS X 0212-1990 - kuten coding (nn-nn)
     * jis213 - JIS X 0213-2000 - kuten coding (p-nn-nn)
     * ucs - Unicode 4.0 - hex coding (4 or 5 hexadecimal digits)
     */
    val charset: CodepointCharsetEnum
)
