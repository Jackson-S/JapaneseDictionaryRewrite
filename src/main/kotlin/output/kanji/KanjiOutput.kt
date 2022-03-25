package output.kanji

import Configuration
import common.Language
import kanjidic.KanjiDic2
import kanjidic.datatypes.CharacterElement
import org.w3c.dom.Document
import org.w3c.dom.Element
import output.common.Output
import output.common.OutputFile
import output.common.OutputFileImpl
import output.kanji.templates.auxiliary.Makefile
import output.kanji.templates.auxiliary.Preferences
import output.kanji.templates.auxiliary.PropertyList
import output.kanji.templates.auxiliary.Stylesheet
import output.kanji.templates.kanji.KanjiPage
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class KanjiOutput(
    kanjiDic2: KanjiDic2,
    languages: List<Language>
) : OutputFile, Output {
    private companion object {
        const val DICTIONARY_TAG = "d:dictionary"
        const val ENTRY_TAG = "d:entry"
        const val INDEX_TAG = "d:index"
        const val DICTIONARY_NAMESPACE_URI = "http://www.apple.com/DTDs/DictionaryService-1.0.rng"
        const val XHTML_NAMESPACE_URI = "http://www.w3.org/1999/xhtml"
        const val TITLE_ATTRIBUTE = "d:title"
        const val VALUE_ATTRIBUTE = "d:value"
        const val YOMI_ATTRIBUTE = "d:yomi"
        const val ID_ATTRIBUTE = "id"
    }

    private val outputDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    private val outputDocument = outputDocumentBuilder.newDocument()
    private val documentRoot = outputDocument.createElementNS(XHTML_NAMESPACE_URI, DICTIONARY_TAG)
    private val debugOutputList = mutableListOf<Pair<String, Element>>()

    init {
        documentRoot.setAttribute("xmlns:d", DICTIONARY_NAMESPACE_URI)

        val entries = if (Configuration.DEBUG_OUTPUT)
            kanjiDic2.entries.filter { Configuration.DEBUG_OUTPUT_ENTRIES.contains(it.literal) }
        else
            kanjiDic2.entries

        entries.forEach { entry ->
            val entryHtml = KanjiPage(outputDocument, languages, entry).page
            val entryNode = outputDocument.createElement(
                ENTRY_TAG,
                Pair(TITLE_ATTRIBUTE, entry.literal),
                Pair(ID_ATTRIBUTE, entry.literal)
            )

            if (Configuration.DEBUG_OUTPUT_ENTRIES.contains(entry.literal)) {
                debugOutputList.add(Pair(entry.literal, entryHtml))
            }

            createIndicies(entry).forEach {
                entryNode.appendChild(it)
            }
            entryNode.appendChild(entryHtml)
            documentRoot.appendChild(entryNode)
        }
    }

    private fun createIndicies(entry: CharacterElement): List<Element> {
        val primaryIndex = outputDocument.createIndex(entry.literal)
        val readingIndices = entry.readingMeaning?.flatMap { readingMeaning ->
            readingMeaning.reading?.map { reading ->
                outputDocument.createIndex(reading.element, entry.literal)
            } ?: emptyList()
        }

        val result = readingIndices?.toMutableList() ?: mutableListOf()
        result.add(primaryIndex)
        return result
    }

    private fun Document.createIndex(value: String, title: String? = null, yomi: String? = null): Element {
        val attributes = listOfNotNull(
            Pair(VALUE_ATTRIBUTE, value),
            title?.let { Pair(TITLE_ATTRIBUTE, it) },
            yomi?.let { Pair(YOMI_ATTRIBUTE, yomi) }
        ).toTypedArray()

        return createElement(INDEX_TAG, *attributes)
    }

    private fun Document.createElement(tagName: String, vararg attributes: Pair<String, String>): Element {
        val element = createElement(tagName)
        attributes.forEach { attribute ->
            element.setAttribute(attribute.first, attribute.second)
        }
        return element
    }

    private fun Element.toOutputString(): String {
        val output = StringWriter()
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, if (Configuration.PRETTY_PRINT_OUTPUT) "yes" else "no")
        transformer.transform(DOMSource(this), StreamResult(output))
        return output.toString()
    }

    override val fileName = "KanjiDictionary.xml"
    override val subdirectory: String? = null
    override fun data(): String = documentRoot.toOutputString()
    override fun outputFiles(): List<OutputFile> = when (Configuration.DEBUG_OUTPUT) {
        false -> listOf(Makefile, Preferences, PropertyList, Stylesheet, this)
        true -> listOf(Makefile, Preferences, PropertyList, Stylesheet, this) +
            debugOutputList.map { (name, data) -> OutputFileImpl("$name.htm", null, data.toOutputString()) }
    }
}
