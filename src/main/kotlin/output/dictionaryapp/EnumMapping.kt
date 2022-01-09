package output.dictionaryapp

import jmdict.enums.BasePartOfSpeechEnum
import jmdict.enums.DialectEnum
import jmdict.enums.FieldEnum
import jmdict.enums.InformationEnum
import jmdict.enums.KanjiInfoEnum
import jmdict.enums.MiscellaneousEnum
import jmdict.enums.PartOfSpeechEnum
import jmdict.enums.ReadingInformationEnum

object EnumMapping {
    fun map(enum: DialectEnum) = Pair(enum.description, null)

    fun map(enum: FieldEnum) = Pair(enum.description, null)

    fun map(enum: KanjiInfoEnum) = when (enum) {
        KanjiInfoEnum.ATEJI -> Pair("Ateji", enum.description)
        KanjiInfoEnum.IRREGULAR_KANA -> Pair("Irregular kana", enum.description)
        KanjiInfoEnum.IRREGULAR_KANJI -> Pair("Irregular kanji", enum.description)
        KanjiInfoEnum.IRREGULAR_OKURIGANA -> Pair("Irregular okurigana", enum.description)
        KanjiInfoEnum.OUTDATED_KANJI_OR_KANA -> Pair("Outdated", enum.description)
        KanjiInfoEnum.RARELY_USED_KANJI -> Pair("Rarely used kanji", enum.description)
    }

    fun map(enum: MiscellaneousEnum) = Pair(enum.name.titlecase(), enum.description)

    fun map(enum: PartOfSpeechEnum) = Pair(enum.name.titlecase(), enum.description)

    fun map(enum: ReadingInformationEnum) = Pair(enum.name.titlecase(), enum.description)

    fun map(enum: BasePartOfSpeechEnum) = Pair(enum.name.titlecase(), null)

    fun map(enum: InformationEnum) = when (enum) {
        is DialectEnum -> map(enum)
        is FieldEnum -> map(enum)
        is MiscellaneousEnum -> map(enum)
        is PartOfSpeechEnum -> map(enum)
        is ReadingInformationEnum -> map(enum)
        is KanjiInfoEnum -> map(enum)
        else -> TODO("Enum type not implemented")
    }

    private fun String.titlecase() =
        (this.uppercase().first() + this.lowercase().substring(1)).replace("_", " ")
}
