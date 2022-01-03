package kanjidic.enums

enum class CodepointCharsetEnum(val code: String) {
    JIS208("jis208"),
    JIS212("jis212"),
    JIS213("jis213"),
    UNICODE("ucs");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
