package jmdict.enums

enum class DialectEnum(override val code: String, override val description: String) : InformationEnum {
    BRAZILIAN("bra", "Brazilian"),
    KYOTO_BEN("kyb", "Kyoto-ben"),
    OSAKA_BEN("osb", "Osaka-ben"),
    KANSAI_BEN("ksb", "Kansai-ben"),
    KANTOU_BEN("ktb", "Kantou-ben"),
    TOSA_BEN("tsb", "Tosa-ben"),
    TOHOKU_BEN("thb", "Touhoku-ben"),
    TSUGARU_BEN("tsug", "Tsugaru-ben"),
    KYUUSHUU_BEN("kyu", "Kyuushuu-ben"),
    RYUUKYUU_BEN("rkb", "Ryuukyuu-ben"),
    NAGANO_BEN("nab", "Nagano-ben"),
    HOKKAIDO_BEN("hob", "Hokkaido-ben");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
