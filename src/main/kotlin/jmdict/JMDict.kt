package jmdict

import Configuration
import common.Language
import jmdict.datatypes.EntryElement
import jmdict.parsers.Entry
import jmdict.parsers.Reading
import jmdict.parsers.Sense
import loader.Loader
import xmlreader.impl.TagImpl

class JMDict(
    loader: Loader
) {
    val entries: List<EntryElement>
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

    private fun getBaseWord(word: String) =
        word.replace("\\([^)]*\\)".toRegex(), "")

    private fun postProcess(entry: EntryElement, dictionary: JMDict) {
        Reading.postProcess(entry)
        Sense.postProcess(entry, dictionary)
    }

    private fun headWord(entry: EntryElement) =
        entry.kanjiElements?.first()?.element?.first() ?: entry.readingElement.first().element.first()

    override fun toString(): String = entries.joinToString { it.toString() }
}
