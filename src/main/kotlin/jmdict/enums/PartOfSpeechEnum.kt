package jmdict.enums

import jmdict.exceptions.MissingEnumException

enum class PartOfSpeechEnum(
    override val code: String,
    override val description: String,
    val basePartOfSpeech: BasePartOfSpeechEnum? = null
) : InformationEnum {
    ADJECTIVE_F("adj-f", "noun or verb acting prenominally", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_I("adj-i", "adjective (keiyoushi)", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_IX("adj-ix", "adjective (keiyoushi) - yoi/ii class", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_KARI("adj-kari", "'kari' adjective (archaic)", BasePartOfSpeechEnum.ADJECTIVE),
    KU_ADJECTIVE("adj-ku", "'ku' adjective (archaic)", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_NA("adj-na", "adjectival nouns or quasi-adjectives (keiyodoshi)", BasePartOfSpeechEnum.ADJECTIVE),
    NARI_ADJECTIVE("adj-nari", "archaic/formal form of na-adjective", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_NO("adj-no", "nouns which may take the genitive case particle 'no'", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_PN("adj-pn", "pre-noun adjectival (rentaishi)", BasePartOfSpeechEnum.ADJECTIVE),
    SHIKU_ADJECTIVE("adj-shiku", "'shiku' adjective (archaic)", BasePartOfSpeechEnum.ADJECTIVE),
    ADJECTIVE_T("adj-t", "'taru' adjective", BasePartOfSpeechEnum.ADJECTIVE),
    ADVERB("adv", "adverb (fukushi)", BasePartOfSpeechEnum.ADVERB),
    ADVERB_TAKING_TO("adv-to", "adverb taking the 'to' particle", BasePartOfSpeechEnum.ADVERB),
    AUXILIARY("aux", "auxiliary"),
    AUXILIARY_ADJECTIVE("aux-adj", "auxiliary adjective", BasePartOfSpeechEnum.ADJECTIVE),
    AUXILIARY_VERB("aux-v", "auxiliary verb", BasePartOfSpeechEnum.VERB),
    CONJUNCTION("conj", "conjunction"),
    COPULA("cop-da", "copula"),
    COUNTER("ctr", "counter"),
    EXPRESSION("exp", "expressions (phrases, clauses, etc.)"),
    INTERJECTION("int", "interjection (kandoushi)"),
    NOUN("n", "noun (common) (futsuumeishi)", BasePartOfSpeechEnum.NOUN),
    NOUN_ADVERBIAL("n-adv", "adverbial noun (fukushitekimeishi)", BasePartOfSpeechEnum.NOUN),
    NOUN_PROPER("n-pr", "proper noun", BasePartOfSpeechEnum.NOUN),
    NOUN_PREFIX("n-pref", "noun, used as a prefix", BasePartOfSpeechEnum.NOUN),
    NOUN_SUFFIX("n-suf", "noun, used as a suffix", BasePartOfSpeechEnum.NOUN),
    NOUN_TEMPORAL("n-t", "noun (temporal) (jisoumeishi)", BasePartOfSpeechEnum.NOUN),
    NUMERIC("num", "numeric"),
    PRONOUN("pn", "pronoun", BasePartOfSpeechEnum.NOUN),
    PREFIX("pref", "prefix", BasePartOfSpeechEnum.PARTICLE),
    PARTICLE("prt", "particle", BasePartOfSpeechEnum.PARTICLE),
    SUFFIX("suf", "suffix", BasePartOfSpeechEnum.PARTICLE),
    UNCLASSIFIED("unc", "unclassified"),
    UNSPECIFIED_VERB("v-unspec", "verb unspecified", BasePartOfSpeechEnum.VERB),
    ICHIDAN("v1", "Ichidan verb", BasePartOfSpeechEnum.VERB),
    ICHIDAN_KURERU("v1-s", "Ichidan verb - kureru special class", BasePartOfSpeechEnum.VERB),
    YODAN_RU("v4r", "Yodan verb with 'ru' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_KU_UPPER_ARCHAIC("v4k", "Yodan verb with 'ku' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_GU_UPPER_ARCHAIC("v4g", "Yodan verb with 'gu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_SU_UPPER_ARCHAIC("v4s", "Yodan verb with 'su' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_TSU_UPPER_ARCHAIC("v4t", "Yodan verb with 'tsu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_NU_UPPER_ARCHAIC("v4n", "Yodan verb with 'nu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_BU_UPPER_ARCHAIC("v4b", "Yodan verb with 'bu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    YODAN_MU_UPPER_ARCHAIC("v4m", "Yodan verb with 'mu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_KU_UPPER_ARCHAIC("v2k-k", "Nidan verb (upper class) with 'ku' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_GU_UPPER_ARCHAIC("v2g-k", "Nidan verb (upper class) with 'gu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_TSU_UPPER_ARCHAIC("v2t-k", "Nidan verb (upper class) with 'tsu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_DZU_UPPER_ARCHAIC("v2d-k", "Nidan verb (upper class) with 'dzu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_HU_FU_UPPER_ARCHAIC("v2h-k", "Nidan verb (upper class) with 'hu/fu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_BU_UPPER_ARCHAIC("v2b-k", "Nidan verb (upper class) with 'bu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_MU_UPPER_ARCHAIC("v2m-k", "Nidan verb (upper class) with 'mu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_YU_UPPER_ARCHAIC("v2y-k", "Nidan verb (upper class) with 'yu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_RU_UPPER_ARCHAIC("v2r-k", "Nidan verb (upper class) with 'ru' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_KU_LOWER_ARCHAIC("v2k-s", "Nidan verb (lower class) with 'ku' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_GU_LOWER_ARCHAIC("v2g-s", "Nidan verb (lower class) with 'gu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_SU_LOWER_ARCHAIC("v2s-s", "Nidan verb (lower class) with 'su' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_ZU_LOWER_ARCHAIC("v2z-s", "Nidan verb (lower class) with 'zu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_TSU_LOWER_ARCHAIC("v2t-s", "Nidan verb (lower class) with 'tsu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_DZU_LOWER_ARCHAIC("v2d-s", "Nidan verb (lower class) with 'dzu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_NU_LOWER_ARCHAIC("v2n-s", "Nidan verb (lower class) with 'nu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_HU_FU_LOWER_ARCHAIC("v2h-s", "Nidan verb (lower class) with 'hu/fu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_BU_LOWER_ARCHAIC("v2b-s", "Nidan verb (lower class) with 'bu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_MU_LOWER_ARCHAIC("v2m-s", "Nidan verb (lower class) with 'mu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_YU_LOWER_ARCHAIC("v2y-s", "Nidan verb (lower class) with 'yu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_RU_LOWER_ARCHAIC("v2r-s", "Nidan verb (lower class) with 'ru' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_U_WE_LOWER_ARCHAIC("v2w-s", "Nidan verb (lower class) with 'u' ending and 'we' conjugation (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_U("v2a-s", "Nidan verb with 'u' ending (archaic)", BasePartOfSpeechEnum.VERB),
    NIDAN_HU_FU("v4h", "Yodan verb with 'hu/fu' ending (archaic)", BasePartOfSpeechEnum.VERB),
    GODAN_ARU("v5aru", "Godan verb - -aru special class", BasePartOfSpeechEnum.VERB),
    GODAN_BU("v5b", "Godan verb with 'bu' ending", BasePartOfSpeechEnum.VERB),
    GODAN_GU("v5g", "Godan verb with 'gu' ending", BasePartOfSpeechEnum.VERB),
    GODAN_KU("v5k", "Godan verb with 'ku' ending", BasePartOfSpeechEnum.VERB),
    GODAN_IKU_YUKU("v5k-s", "Godan verb - Iku/Yuku special class", BasePartOfSpeechEnum.VERB),
    GODAN_MU("v5m", "Godan verb with 'mu' ending", BasePartOfSpeechEnum.VERB),
    GODAN_NU("v5n", "Godan verb with 'nu' ending", BasePartOfSpeechEnum.VERB),
    GODAN_RU("v5r", "Godan verb with 'ru' ending", BasePartOfSpeechEnum.VERB),
    GODAN_RU_IRREGULAR("v5r-i", "Godan verb with 'ru' ending (irregular verb)", BasePartOfSpeechEnum.VERB),
    GODAN_SU("v5s", "Godan verb with 'su' ending", BasePartOfSpeechEnum.VERB),
    GODAN_TSU("v5t", "Godan verb with 'tsu' ending", BasePartOfSpeechEnum.VERB),
    GODAN_U("v5u", "Godan verb with 'u' ending", BasePartOfSpeechEnum.VERB),
    GODAN_U_SPECIAL("v5u-s", "Godan verb with 'u' ending (special class)", BasePartOfSpeechEnum.VERB),
    GODAN_URU("v5uru", "Godan verb - Uru old class verb (old form of Eru)", BasePartOfSpeechEnum.VERB),
    INTRANSIVIVE_VERB("vi", "intransitive verb", BasePartOfSpeechEnum.VERB),
    KURU_VERB("vk", "Kuru verb - special class", BasePartOfSpeechEnum.VERB),
    IRREGULAR_NU_VERB("vn", "irregular nu verb", BasePartOfSpeechEnum.VERB),
    IRREGULAR_RU_RI_END_VERB("vr", "irregular ru verb, plain form ends with -ri", BasePartOfSpeechEnum.VERB),
    NOUN_PARTICIPLE_SURU("vs", "noun or participle which takes the aux. verb suru", BasePartOfSpeechEnum.VERB),
    SU_VERB("vs-c", "su verb - precursor to the modern suru", BasePartOfSpeechEnum.VERB),
    SURU_VERB_INCLUDED("vs-i", "suru verb - included", BasePartOfSpeechEnum.VERB),
    SURU_VERB_SPECIAL("vs-s", "suru verb - special class", BasePartOfSpeechEnum.VERB),
    TRANSITIVE_VERB("vt", "transitive verb", BasePartOfSpeechEnum.VERB),
    GODAN_ZURU("vz", "Ichidan verb - zuru verb (alternative form of -jiru verbs)", BasePartOfSpeechEnum.VERB);

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw MissingEnumException(value)
        }
    }
}
