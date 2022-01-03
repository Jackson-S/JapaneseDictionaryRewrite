package kanjidic.datatypes

import kanjidic.enums.RadicalTypeEnum

data class RadicalElement(
    /**
     * The radical number, in the range 1 to 214. The particular
     * classification type is stated in the rad_type attribute.
     */
    val value: Int,

    /**
     * The rad_type attribute states the type of radical classification.
     * classical - as recorded in the KangXi Zidian.
     * nelson_c - as used in the Nelson "Modern Japanese-English
     * Character Dictionary" (i.e. the Classic, not the New Nelson).
     * This will only be used where Nelson reclassified the kanji.
     */
    val type: RadicalTypeEnum
)
