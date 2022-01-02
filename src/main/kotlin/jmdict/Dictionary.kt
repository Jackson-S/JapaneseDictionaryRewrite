package jmdict

import jmdict.enums.EntryElement
import jmdict.parsers.Entry
import jmdict.parsers.Reading
import jmdict.parsers.Sense
import loader.Loader
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class Dictionary(
    loader: Loader
) {
    private val entry: List<EntryElement>
    private val entryByIndex: MutableMap<Int, EntryElement> = mutableMapOf()
    private val entryByHeadWord: MutableMap<String, MutableList<EntryElement>> = mutableMapOf()

    init {
        val xmlFile = File(loader.path())
        val xmlInput = InputSource(StringReader(xmlFile.readText()))
        val root = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlInput).getElementsByTagName("JMdict").first()

        entry = root!!.childNodes.map {
            val element = it as? Element

            if (element == null) {
                null
            } else {
                Entry.parse(element)
            }
        }.filterNotNull()

        entry.forEach {
            entryByIndex[it.entrySequence] = it

            val headWord = headWord(it)
            if (entryByHeadWord.contains(headWord)) {
                entryByHeadWord[headWord]!!.add(it)
            } else {
                entryByHeadWord[headWord] = mutableListOf(it)
            }
        }

        entry.forEach {
            postProcess(it, this)
        }
    }

    fun entry(headWord: String) = entryByHeadWord[headWord]

    fun entries() = entryByHeadWord.keys

    private fun postProcess(entry: EntryElement, dictionary: Dictionary) {
        Reading.postProcess(entry)
        Sense.postProcess(entry, dictionary)
    }

    private fun headWord(entry: EntryElement) =
        entry.kanjiElement?.first()?.element?.first() ?: entry.readingElement.first().element.first()

    override fun toString(): String = entry.map { it.toString() }.joinToString()
}
