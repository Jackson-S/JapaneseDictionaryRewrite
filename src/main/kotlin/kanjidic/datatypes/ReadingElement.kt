package kanjidic.datatypes

import kanjidic.enums.ReadingTypeEnum

data class ReadingElement(
    /**
     * The reading element contains the reading or pronunciation
     * of the kanji.
     */
    val value: String,

    /**
     * The r_type attribute defines the type of reading in the reading
     * element. The current values are:
     * pinyin - the modern PinYin romanization of the Chinese reading
     * of the kanji. The tones are represented by a concluding
     * digit.
     * korean_r - the romanized form of the Korean reading(s) of the
     * kanji.  The readings are in the (Republic of Korea) Ministry
     * of Education style of romanization.
     * korean_h - the Korean reading(s) of the kanji in hangul.
     * ja_on - the "on" Japanese reading of the kanji, in katakana.
     * Another attribute r_status, if present, will indicate with
     * a value of "jy" whether the reading is approved for a
     * "Jouyou kanji".
     * A further attribute on_type, if present,  will indicate with
     * a value of kan, go, tou or kan'you the type of on-reading.
     * ja_kun - the "kun" Japanese reading of the kanji, usually in
     * hiragana.
     * Where relevant the okurigana is also included separated by a
     * ".". Readings associated with prefixes and suffixes are
     * marked with a "-". A second attribute r_status, if present,
     * will indicate with a value of "jy" whether the reading is
     * approved for a "Jouyou kanji".
     */
    val readingType: ReadingTypeEnum,

    /**
     * See under ja_on above.
     */
    val onType: String?,

    /**
     * See under ja_on and ja_kun above.
     */
    val readingStatus: String?
)
