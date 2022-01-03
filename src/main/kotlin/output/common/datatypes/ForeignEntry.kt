package output.common.datatypes

import common.Language
import output.common.enums.EntryTypeEnum

data class ForeignEntry(
    override val title: String,

    override val language: Language,

    val translations: List<Translation>
) : Entry {
    override val entryType: EntryTypeEnum
        get() = EntryTypeEnum.DEFINITION
}
