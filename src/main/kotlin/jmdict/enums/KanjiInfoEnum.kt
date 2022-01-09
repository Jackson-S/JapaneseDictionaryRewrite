package jmdict.enums

enum class KanjiInfoEnum(override val code: String, override val description: String) : InformationEnum {
    ATEJI("ateji", "ateji (phonetic) reading"),
    IRREGULAR_KANA("ik", "word containing irregular kana usage"),
    IRREGULAR_KANJI("iK", "word containing irregular kanji usage"),
    IRREGULAR_OKURIGANA("io", "irregular okurigana usage"),
    OUTDATED_KANJI_OR_KANA("oK", "word containing out-dated kanji or kanji usage"),
    RARELY_USED_KANJI("rK", "rarely-used kanji form");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
