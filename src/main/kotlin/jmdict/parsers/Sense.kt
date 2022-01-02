package jmdict.parsers

import jmdict.Dictionary
import jmdict.datatypes.KanjiElement
import jmdict.datatypes.ReadingElement
import jmdict.datatypes.Reference
import jmdict.datatypes.Referrable
import jmdict.datatypes.SenseElement
import jmdict.enums.DialectEnum
import jmdict.enums.EntryElement
import jmdict.enums.FieldEnum
import jmdict.enums.PartOfSpeechEnum
import jmdict.map
import org.w3c.dom.Element

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

    fun parse(element: Element): SenseElement =
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

    private fun restrictedKanji(element: Element) =
        element.getElementsByTagName(RESTRICTED_KANJI).map { Reference<KanjiElement>(it.textContent) }

    private fun restrictedReading(element: Element) =
        element.getElementsByTagName(RESTRICTED_READING).map { Reference<ReadingElement>(it.textContent) }

    private fun crossReference(element: Element) =
        element.getElementsByTagName(CROSS_REFERENCE).map { Reference<Referrable>(it.textContent) }

    private fun antonym(element: Element) =
        element.getElementsByTagName(ANTONYM).map { Reference<Referrable>(it.textContent) }

    private fun partOfSpeech(element: Element) =
        element.getElementsByTagName(PART_OF_SPEECH).map { PartOfSpeechEnum.from(it.textContent) }

    private fun field(element: Element) =
        element.getElementsByTagName(FIELD).map { FieldEnum.from(it.textContent) }

    private fun misc(element: Element) =
        element.getElementsByTagName(MISC).map { it.textContent }.ifEmpty { null }

    private fun sourceLanguage(element: Element) =
        element.getElementsByTagName(LANGUAGE_SOURCE).map { SourceLanguage.parse(it as Element) }

    private fun dialect(element: Element) =
        element.getElementsByTagName(DIALECT).map { DialectEnum.from(it.textContent) }

    private fun gloss(element: Element) =
        element.getElementsByTagName(GLOSS).map { Gloss.parse(it as Element) }

    private fun information(element: Element) =
        element.getElementsByTagName(INFORMATION).map { it.textContent }.ifEmpty { null }

    fun postProcess(entry: EntryElement, dictionary: Dictionary) {
        entry.senseElement.forEach { senseElement ->
            senseElement.restrictedKanji?.forEach { restrictedKanji ->
                restrictedKanji.value = entry.kanjiElement!!.first { it.element.contains(restrictedKanji.referralText) }
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
