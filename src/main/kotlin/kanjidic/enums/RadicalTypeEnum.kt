package kanjidic.enums

enum class RadicalTypeEnum(val code: String) {
    CLASSICAL("classical"),
    NELSON_CLASSICAL("nelson_c");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
