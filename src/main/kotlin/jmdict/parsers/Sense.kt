package jmdict.parsers

import jmdict.JMDict
import jmdict.datatypes.EntryElement
import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.Reference
import jmdict.datatypes.Referrable
import jmdict.datatypes.SenseElement
import jmdict.enums.DialectEnum
import jmdict.enums.FieldEnum
import jmdict.enums.PartOfSpeechEnum
import xmlreader.Tag

object Sense {
    private const val RESTRICTED_KANJI = "stagk"
    private const val RESTRICTED_READING = "stagr"
    private const val PART_OF_SPEECH = "pos"
    private const val CROSS_REFERENCE = "xref"
    private const val ANTONYM = "ant"
    private const val FIELD = "field"
    private const val MISC = "misc"
    private const val INFORMATION = "s_inf"
    private const val LANGUAGE_SOURCE = "lsource"
    private const val DIALECT = "dial"
    private const val GLOSS = "gloss"
    private const val CROSS_REFERENCE_DELIMITER = "・"

    fun parse(element: Tag): SenseElement =
        SenseElement(
            restrictedKanji = restrictedKanji(element),
            restrictedReading = restrictedReading(element),
            crossReference = crossReference(element),
            antonym = antonym(element),
            partOfSpeech = partOfSpeech(element),
            field = field(element),
            misc = misc(element),
            sourceLanguage = sourceLanguage(element),
            dialect = dialect(element),
            gloss = gloss(element),
            information = information(element)
        )

    private fun restrictedKanji(element: Tag) =
        element.childrenWithTagName(RESTRICTED_KANJI).map { Reference<KanjiElement>(it.text()) }

    private fun restrictedReading(element: Tag) =
        element.childrenWithTagName(RESTRICTED_READING).map { Reference<ReadingElement>(it.text()) }

    private fun crossReference(element: Tag) =
        element.childrenWithTagName(CROSS_REFERENCE).map { Reference<Referrable>(it.text()) }

    private fun antonym(element: Tag) =
        element.childrenWithTagName(ANTONYM).map { Reference<Referrable>(it.text()) }

    private fun partOfSpeech(element: Tag) =
        element.childrenWithTagName(PART_OF_SPEECH).map { PartOfSpeechEnum.from(it.text()) }

    private fun field(element: Tag) =
        element.childrenWithTagName(FIELD).map { FieldEnum.from(it.text()) }

    private fun misc(element: Tag) =
        element.childrenWithTagName(MISC).map { it.text() }.ifEmpty { null }

    private fun sourceLanguage(element: Tag) =
        element.childrenWithTagName(LANGUAGE_SOURCE).map { SourceLanguage.parse(it) }

    private fun dialect(element: Tag) =
        element.childrenWithTagName(DIALECT).map { DialectEnum.from(it.text()) }

    private fun gloss(element: Tag) =
        element.childrenWithTagName(GLOSS).map { Gloss.parse(it) }

    private fun information(element: Tag) =
        element.childrenWithTagName(INFORMATION).map { it.text() }.ifEmpty { null }

    fun postProcess(entry: EntryElement, dictionary: JMDict) {
        entry.senseElement.forEach { senseElement ->
            senseElement.restrictedKanji?.forEach { restrictedKanji ->
                restrictedKanji.value = entry.kanjiElements!!.first { it.element.contains(restrictedKanji.referralText) }
            }

            senseElement.restrictedReading?.forEach { restrictedReading ->
                restrictedReading.value = entry.readingElement.first { it.element.contains(restrictedReading.referralText) }
            }

            senseElement.crossReference?.forEach { crossReference ->
                val reference = crossReference.referralText!!.split(CROSS_REFERENCE_DELIMITER)
                val referencedEntry = dictionary.entry(reference[0])

                crossReference.value = when (reference.size) {
                    1 -> referencedEntry?.first()
                    2 -> if (reference[1].toIntOrNull() != null) {
                        referencedEntry?.first()?.senseElement?.getOrNull(reference[1].toInt() - 1)
                    } else {
                        referencedEntry?.first()?.readingElement?.firstOrNull { it.element.contains(reference[1]) }
                    }
                    3 -> referencedEntry?.firstOrNull { it.readingElement.any { it.element.contains(reference[1]) } }
                    else -> null
                }
            }
        }
    }
}
