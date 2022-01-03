package jmdict

import common.Language
import jmdict.datatypes.EntryElement
import jmdict.datatypes.SenseElement
import jmdict.parsers.Entry
import jmdict.parsers.Reading
import jmdict.parsers.Sense
import loader.Loader
import xmlreader.impl.TagImpl

class Dictionary(
    loader: Loader
) {
    private val entries: List<EntryElement>
    private val entryByIndex: MutableMap<Int, EntryElement> = mutableMapOf()
    private val entryByHeadWord: MutableMap<String, MutableList<EntryElement>> = mutableMapOf()

    init {
        val root = TagImpl(loader, "JMdict")

        entries = root.children().map {
            Entry.parse(it)
        }

        entries.forEach {
            entryByIndex[it.entrySequence] = it

            val headWord = headWord(it)
            if (entryByHeadWord.contains(headWord)) {
                entryByHeadWord[headWord]!!.add(it)
            } else {
                entryByHeadWord[headWord] = mutableListOf(it)
            }
        }

        entries.forEach {
            postProcess(it, this)
        }
    }

    fun entry(headWord: String) = entryByHeadWord[headWord]

    fun entries() = entryByHeadWord.keys

    private fun getBaseWord(word: String) =
        word.replace("\\([^)]*\\)".toRegex(), "")

    fun nonJapaneseHeadwords(language: Language): Map<String, List<SenseElement>> {
        val result = mutableMapOf<String, MutableList<SenseElement>>()

        entries.forEach { entry ->
            entry.senseElement.forEach { sense ->
                for (gloss in sense.gloss ?: listOf()) {
                    if (gloss.language != language) continue
                    if (gloss.element.length > 32) continue
                    val baseWord = getBaseWord(gloss.element)
                    if (baseWord.isBlank()) continue
                    if (result.containsKey(baseWord)) {
                        result[baseWord]!!.add(sense)
                    } else {
                        result[baseWord] = mutableListOf(sense)
                    }
                }
            }
        }

        return result
    }

    private fun postProcess(entry: EntryElement, dictionary: Dictionary) {
        Reading.postProcess(entry)
        Sense.postProcess(entry, dictionary)
    }

    private fun headWord(entry: EntryElement) =
        entry.kanjiElement?.first()?.element?.first() ?: entry.readingElement.first().element.first()

    override fun toString(): String = entries.joinToString { it.toString() }
}
