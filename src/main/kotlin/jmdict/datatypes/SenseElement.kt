package jmdict.datatypes

import jmdict.enums.DialectEnum
import jmdict.enums.FieldEnum
import jmdict.enums.MiscellaneousEnum
import jmdict.enums.PartOfSpeechEnum

/**
 * The sense element will record the translational equivalent
 * of the Japanese word, plus other related information. Where there
 * are several distinctly different meanings of the word, multiple
 * sense elements will be employed.
 */
data class SenseElement(
    /**
     * These elements, if present, indicate that the sense is restricted
     * to the lexeme represented by the keb and/or reb.
     */
    val restrictedKanji: List<Reference<KanjiElement>>?,

    /**
     * These elements, if present, indicate that the sense is restricted
     * to the lexeme represented by the keb and/or reb.
     */
    val restrictedReading: List<Reference<ReadingElement>>?,

    /**
     * This element is used to indicate a cross-reference to another
     * entry with a similar or related meaning or sense. The content of
     * this element is typically a keb or reb element in another entry. In some
     * cases a keb will be followed by a reb and/or a sense number to provide
     * a precise target for the cross-reference. Where this happens, a JIS
     * "centre-dot" (0x2126) is placed between the components of the
     * cross-reference. The target keb or reb must not contain a centre-dot.
     */
    val crossReference: List<Reference<Referrable>>?,

    /**
     * This element is used to indicate another entry which is an
     * antonym of the current entry/sense. The content of this element
     * must exactly match that of a keb or reb element in another entry.
     */
    val antonym: List<Reference<Referrable>>?,

    /**
     * Part-of-speech information about the entry/sense. Should use
     * appropriate entity codes. In general where there are multiple senses
     * in an entry, the part-of-speech of an earlier sense will apply to
     * later senses unless there is a new part-of-speech indicated.
     */
    val partOfSpeech: List<PartOfSpeechEnum>?,

    /**
     * Information about the field of application of the entry/sense.
     * When absent, general application is implied. Entity coding for
     * specific fields of application.
     */
    val field: List<FieldEnum>?,

    /**
     * This element is used for other relevant information about
     * the entry/sense. As with part-of-speech, information will usually
     * apply to several senses.
     */
    val misc: List<MiscellaneousEnum>?,

    /**
     * This element records the information about the source
     * language(s) of a loan-word/gairaigo. If the source language is other
     * than English, the language is indicated by the xml:lang attribute.
     * The element value (if any) is the source word or phrase.
     */
    val sourceLanguage: List<SourceLanguageElement>?,

    /**
     * For words specifically associated with regional dialects in
     * Japanese, the entity code for that dialect, e.g. ksb for Kansaiben.
     */
    val dialect: List<DialectEnum>?,

    /**
     * Within each sense will be one or more "glosses", i.e.
     * target-language words or phrases which are equivalents to the
     * Japanese word. This element would normally be present, however it
     * may be omitted in entries which are purely for a cross-reference.
     */
    val gloss: List<GlossElement>?,

    /**
     * The sense-information elements provided for additional
     * information to be recorded about a sense. Typical usage would
     * be to indicate such things as level of currency of a sense, the
     * regional variations, etc.
     */
    val information: List<String>?
) : Referrable
