enum class Language(val code: String) {
    DUTCH("dut"),
    ENGLISH("eng"),
    FRENCH("fre"),
    GERMAN("ger"),
    HUNGARIAN("hun"),
    RUSSIAN("rus"),
    SLOVENIAN("slv"),
    SPANISH("spa"),
    SWEDISH("swe");

    companion object {
        fun fromCode(code: String) = values().first { it.code == code }
    }
}
