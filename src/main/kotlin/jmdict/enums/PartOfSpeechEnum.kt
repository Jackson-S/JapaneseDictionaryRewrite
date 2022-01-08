package jmdict.enums

enum class PartOfSpeechEnum(override val code: String, override val description: String) : InformationEnum {
    ABBREVIATION("abbr", "abbreviation"),
    ADJECTIVE_I("adj-i", "adjective (keiyoushi)"),
    ADJECTIVE_IX("adj-ix", "adjective (keiyoushi) - yoi/ii class"),
    ADJECTIVE_NA("adj-na", "adjectival nouns or quasi-adjectives (keiyodoshi)"),
    ADJECTIVE_NO("adj-no", "nouns which may take the genitive case particle 'no'"),
    ADJECTIVE_PN("adj-pn", "pre-noun adjectival (rentaishi)"),
    ADJECTIVE_T("adj-t", "'taru' adjective"),
    ADJECTIVE_F("adj-f", "noun or verb acting prenominally"),
    ADVERB("adv", "adverb (fukushi)"),
    ADVERB_TAKING_TO("adv-to", "adverb taking the 'to' particle"),
    ARCHAISM("arch", "archaism"),
    ATEJI("ateji", "ateji (phonetic) reading"),
    AUXILIARY("aux", "auxiliary"),
    AUXILIARY_VERB("aux-v", "auxiliary verb"),
    AUXILIARY_ADJECTIVE("aux-adj", "auxiliary adjective"),
    COLLOQUIALISM("col", "colloquialism"),
    CONJUNCTION("conj", "conjunction"),
    COPULA("cop-da", "copula"),
    COUNTER("ctr", "counter"),
    EXCLUSIVELY_KANJI("eK", "exclusively kanji"),
    EXCLUSIVELY_KANA("ek", "exclusively kana"),
    EXPRESSION("exp", "expressions (phrases, clauses, etc.)"),
    FAMILIAR("fam", "familiar language"),
    FEMALE("fem", "female term or language"),
    GIKUN("gikun", "gikun (meaning as reading) or jukujikun (special kanji reading)"),
    HONORIFIC("hon", "honorific or respectful (sonkeigo) language"),
    HUMBLE("hum", "humble (kenjougo) language"),
    IRREGULAR_KANJI("iK", "word containing irregular kanji usage"),
    IDIOMATIC("id", "idiomatic expression"),
    IRREGULAR_KANA("ik", "word containing irregular kana usage"),
    INTERJECTION("int", "interjection (kandoushi)"),
    IRREGULAR_OKURIGANA("io", "irregular okurigana usage"),
    IRREGULAR_VERB("iv", "irregular verb"),
    MANGA_SLANG("m-sl", "manga slang"),
    MALE("male", "male term or language"),
    MALE_SLANG("male-sl", "male slang"),
    NOUN("n", "noun (common) (futsuumeishi)"),
    ADVERBIAL_NOUN("n-adv", "adverbial noun (fukushitekimeishi)"),
    SUFFIX_NOUN("n-suf", "noun, used as a suffix"),
    PREFIX_NOUN("n-pref", "noun, used as a prefix"),
    TEMPORAL_NOUN("n-t", "noun (temporal) (jisoumeishi)"),
    NUMERIC("num", "numeric"),
    OUTDATED_KANJI("oK", "word containing out-dated kanji"),
    OBSOLETE_TERM("obs", "obsolete term"),
    OBSCURE_TERM("obsc", "obscure term"),
    OUTDATED_OBSOLETE_KANA("ok", "out-dated or obsolete kana usage"),
    OLD_IRREGULAR_KANA("oik", "old or irregular kana form"),
    ONOMATOPOEIC("on-mim", "onomatopoeic or mimetic word"),
    PRONOUN("pn", "pronoun"),
    POETICAL("poet", "poetical term"),
    POLITE("pol", "polite (teineigo) language"),
    PREFIX("pref", "prefix"),
    PROVERB("proverb", "proverb"),
    PARTICLE("prt", "particle"),
    QUOTE("quote", "quotation"),
    RARE("rare", "rare"),
    SENSITIVE("sens", "sensitive"),
    SLANG("sl", "slang"),
    SUFFIX("suf", "suffix"),
    USUALLY_KANJI("uK", "word usually written using kanji alone"),
    USUALLY_KANA("uk", "word usually written using kana alone"),
    UNCLASSIFIED("unc", "unclassified"),
    YOYOJI("yoji", "yojijukugo"),
    ICHIDAN("v1", "Ichidan verb"),
    ICHIDAN_KURERU("v1-s", "Ichidan verb - kureru special class"),
    NIDAN_U("v2a-s", "Nidan verb with 'u' ending (archaic)"),
    NIDAN_HU_FU("v4h", "Yodan verb with 'hu/fu' ending (archaic)"),
    YODAN_RU("v4r", "Yodan verb with 'ru' ending (archaic)"),
    GODAN_ARU("v5aru", "Godan verb - -aru special class"),
    GODAN_BU("v5b", "Godan verb with 'bu' ending"),
    GODAN_GU("v5g", "Godan verb with 'gu' ending"),
    GODAN_KU("v5k", "Godan verb with 'ku' ending"),
    GODAN_IKU_YUKU("v5k-s", "Godan verb - Iku/Yuku special class"),
    GODAN_MU("v5m", "Godan verb with 'mu' ending"),
    GODAN_NU("v5n", "Godan verb with 'nu' ending"),
    GODAN_RU("v5r", "Godan verb with 'ru' ending"),
    GODAN_RU_IRREGULAR("v5r-i", "Godan verb with 'ru' ending (irregular verb)"),
    GODAN_SU("v5s", "Godan verb with 'su' ending"),
    GODAN_TSU("v5t", "Godan verb with 'tsu' ending"),
    GODAN_U("v5u", "Godan verb with 'u' ending"),
    GODAN_U_SPECIAL("v5u-s", "Godan verb with 'u' ending (special class)"),
    GODAN_URU("v5uru", "Godan verb - Uru old class verb (old form of Eru)"),
    GODAN_ZURU("vz", "Ichidan verb - zuru verb (alternative form of -jiru verbs)"),
    INTRANSIVIVE_VERB("vi", "intransitive verb"),
    KURU_VERB("vk", "Kuru verb - special class"),
    IRREGULAR_NU_VERB("vn", "irregular nu verb"),
    IRREGULAR_RU_RI_END_VERB("vr", "irregular ru verb, plain form ends with -ri"),
    NOUN_PARTICIPLE_SURU("vs", "noun or participle which takes the aux. verb suru"),
    SU_VERB("vs-c", "su verb - precursor to the modern suru"),
    SURU_VERB_SPECIAL("vs-s", "suru verb - special class"),
    SURU_VERB_INCLUDED("vs-i", "suru verb - included"),
    TRANSITIVE_VERB("vt", "transitive verb"),
    VULGAR("vulg", "vulgar expression or word"),
    KARI_ADJECTIVE("adj-kari", "'kari' adjective (archaic)"),
    KU_ADJECTIVE("adj-ku", "'ku' adjective (archaic)"),
    SHIKU_ADJECTIVE("adj-shiku", "'shiku' adjective (archaic)"),
    NARI_ADJECTIVE("adj-nari", "archaic/formal form of na-adjective"),
    PROPER_NOUN("n-pr", "proper noun"),
    UNSPECIFIED_VERB("v-unspec", "verb unspecified"),
    YODAN_KU_UPPER_ARCHAIC("v4k", "Yodan verb with 'ku' ending (archaic)"),
    YODAN_GU_UPPER_ARCHAIC("v4g", "Yodan verb with 'gu' ending (archaic)"),
    YODAN_SU_UPPER_ARCHAIC("v4s", "Yodan verb with 'su' ending (archaic)"),
    YODAN_TSU_UPPER_ARCHAIC("v4t", "Yodan verb with 'tsu' ending (archaic)"),
    YODAN_NU_UPPER_ARCHAIC("v4n", "Yodan verb with 'nu' ending (archaic)"),
    YODAN_BU_UPPER_ARCHAIC("v4b", "Yodan verb with 'bu' ending (archaic)"),
    YODAN_MU_UPPER_ARCHAIC("v4m", "Yodan verb with 'mu' ending (archaic)"),
    NIDAN_KU_UPPER_ARCHAIC("v2k-k", "Nidan verb (upper class) with 'ku' ending (archaic)"),
    NIDAN_GU_UPPER_ARCHAIC("v2g-k", "Nidan verb (upper class) with 'gu' ending (archaic)"),
    NIDAN_TSU_UPPER_ARCHAIC("v2t-k", "Nidan verb (upper class) with 'tsu' ending (archaic)"),
    NIDAN_DZU_UPPER_ARCHAIC("v2d-k", "Nidan verb (upper class) with 'dzu' ending (archaic)"),
    NIDAN_HU_FU_UPPER_ARCHAIC("v2h-k", "Nidan verb (upper class) with 'hu/fu' ending (archaic)"),
    NIDAN_BU_UPPER_ARCHAIC("v2b-k", "Nidan verb (upper class) with 'bu' ending (archaic)"),
    NIDAN_MU_UPPER_ARCHAIC("v2m-k", "Nidan verb (upper class) with 'mu' ending (archaic)"),
    NIDAN_YU_UPPER_ARCHAIC("v2y-k", "Nidan verb (upper class) with 'yu' ending (archaic)"),
    NIDAN_RU_UPPER_ARCHAIC("v2r-k", "Nidan verb (upper class) with 'ru' ending (archaic)"),
    NIDAN_KU_LOWER_ARCHAIC("v2k-s", "Nidan verb (lower class) with 'ku' ending (archaic)"),
    NIDAN_GU_LOWER_ARCHAIC("v2g-s", "Nidan verb (lower class) with 'gu' ending (archaic)"),
    NIDAN_SU_LOWER_ARCHAIC("v2s-s", "Nidan verb (lower class) with 'su' ending (archaic)"),
    NIDAN_ZU_LOWER_ARCHAIC("v2z-s", "Nidan verb (lower class) with 'zu' ending (archaic)"),
    NIDAN_TSU_LOWER_ARCHAIC("v2t-s", "Nidan verb (lower class) with 'tsu' ending (archaic)"),
    NIDAN_DZU_LOWER_ARCHAIC("v2d-s", "Nidan verb (lower class) with 'dzu' ending (archaic)"),
    NIDAN_NU_LOWER_ARCHAIC("v2n-s", "Nidan verb (lower class) with 'nu' ending (archaic)"),
    NIDAN_HU_FU_LOWER_ARCHAIC("v2h-s", "Nidan verb (lower class) with 'hu/fu' ending (archaic)"),
    NIDAN_BU_LOWER_ARCHAIC("v2b-s", "Nidan verb (lower class) with 'bu' ending (archaic)"),
    NIDAN_MU_LOWER_ARCHAIC("v2m-s", "Nidan verb (lower class) with 'mu' ending (archaic)"),
    NIDAN_YU_LOWER_ARCHAIC("v2y-s", "Nidan verb (lower class) with 'yu' ending (archaic)"),
    NIDAN_RU_LOWER_ARCHAIC("v2r-s", "Nidan verb (lower class) with 'ru' ending (archaic)"),
    NIDAN_U_WE_LOWER_ARCHAIC("v2w-s", "Nidan verb (lower class) with 'u' ending and 'we' conjugation (archaic)");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
