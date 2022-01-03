package output.common.datatypes

import common.Language
import output.common.enums.EntryTypeEnum

interface Entry {
    /**
     * The title of the entry, used as the headword for the dictionary page
     */
    val title: String

    /**
     * The language that the entry is in
     */
    val language: Language

    /**
     * If the page is a word entry, or a miscellanea entry
     */
    val entryType: EntryTypeEnum
}