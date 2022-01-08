package jmdict.enums

enum class ReadingInformationEnum(override val code: String, override val description: String) : InformationEnum {
    GIKUN("gikun", "gikun (meaning as reading) or jukujikun (special kanji reading)"),
    IRREGULAR_KANA("ik", "word containing irregular kana usage"),
    OUTDATED_OR_OBSOLETE_KANA("ok", "out-dated or obsolete kana usage"),
    USUALLY_WRITTEN_WITH_KANJI("uK", "word usually written using kanji alone");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}