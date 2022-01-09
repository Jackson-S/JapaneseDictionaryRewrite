package jmdict.enums

enum class MiscellaneousEnum(override val code: String, override val description: String) : InformationEnum {
    ABBREVIATION("abbr", "abbreviation"),
    ARCHAISM("arch", "archaism"),
    CHARACTER("char", "character"),
    CHILDREN_LANGUAGE("chn", "children's language"),
    COLLOQUIALISM("col", "colloquialism"),
    COMPANY_NAME("company", "company name"),
    CREATURE("creat", "creature"),
    DATED_TERM("dated", "dated term"),
    DEITY("dei", "deity"),
    DEROGATORY("derog", "derogatory"),
    DOCUMENT("doc", "document"),
    EVENT("ev", "event"),
    FAMILIAR("fam", "familiar language"),
    FEMALE("fem", "female term or language"),
    FICTION("fict", "fiction"),
    FORMAL("form", "formal or literary term"),
    GIVEN_NAME("given", "given name or forename, gender not specified"),
    GROUP("group", "group"),
    HISTORICAL("hist", "historical term"),
    HONORIFIC("hon", "honorific or respectful (sonkeigo) language"),
    HUMBLE("hum", "humble (kenjougo) language"),
    IDIOMATIC("id", "idiomatic expression"),
    JOCULAR("joc", "jocular, humorous term"),
    LEGEND("leg", "legend"),
    MANGA_SLANG("m-sl", "manga slang"),
    MALE("male", "male term or language"),
    MYTHOLOGY("myth", "mythology"),
    INTERNET_SLANG("net-sl", "Internet slang"),
    OBJECT("obj", "object"),
    OBSOLETE_TERM("obs", "obsolete term"),
    OBSCURE_TERM("obsc", "obscure term"),
    ONOMATOPOEIC_OR_MIMETIC("on-mim", "onomatopoeic or mimetic word"),
    ORGANIZATION("organization", "organization name"),
    OTHER("oth", "other"),
    PERSON("person", "full name of a particular person"),
    PLACE("place", "place name"),
    POETICAL("poet", "poetical term"),
    POLITE("pol", "polite (teineigo) language"),
    PRODUCT("product", "product name"),
    PROVERB("proverb", "proverb"),
    QUOTE("quote", "quotation"),
    RARE("rare", "rare"),
    RELIGIOUS("relig", "religious"),
    SENSITIVE("sens", "sensitive"),
    SERVICE("serv", "service"),
    SLANG("sl", "slang"),
    STATION("station", "railway station"),
    SURNAME("surname", "family or surname"),
    USUALLY_KANA("uk", "word usually written using kana alone"),
    UNCLASSIFIED_NAME("unclass", "unclassified name"),
    VULGAR("vulg", "vulgar expression or word"),
    WORK("work", "work of art, literature, music, etc. name"),
    X_RATED("X", "rude or X-rated term (not displayed in educational software)"),
    YOYOJI("yoji", "yojijukugo");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
