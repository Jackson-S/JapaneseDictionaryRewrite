package common

enum class Language(val code2: String, val code3: String) {
    DUTCH("nl", "dut"),
    ENGLISH("en", "eng"),
    FRENCH("fr", "fre"),
    GERMAN("de", "ger"),
    HUNGARIAN("hu", "hun"),
    RUSSIAN("ru", "rus"),
    SLOVENIAN("sl", "slv"),
    SPANISH("es", "spa"),
    SWEDISH("sv", "swe"),
    PORTUGUESE("pt", "por"),
    JAPANESE("ja", "jpn");

    companion object {
        fun fromCode(code: String) = values().first { it.code2 == code || it.code3 == code }
    }
}
