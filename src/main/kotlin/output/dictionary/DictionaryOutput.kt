package output.dictionary

import Configuration
import common.Language
import jmdict.JMDict
import jmdict.datatypes.EntryElement
import jmdict.enums.GlossType
import org.w3c.dom.Document
import org.w3c.dom.Element
import output.common.Output
import output.common.OutputFile
import output.common.OutputFileImpl
import output.dictionary.templates.auxiliary.Makefile
import output.dictionary.templates.auxiliary.Preferences
import output.dictionary.templates.auxiliary.PropertyList
import output.dictionary.templates.auxiliary.Stylesheet
import output.dictionary.templates.foreign.ForeignPage
import output.dictionary.templates.japanese.JapanesePage
import sentences.TatoebaSentences
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class DictionaryOutput(
    jmdict: JMDict,
    private val tatoeba: TatoebaSentences,
    private val languages: List<Language>
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
            jmdict.entries.filter { Configuration.DEBUG_OUTPUT_ENTRIES.contains(it.headWord) }
        else
            jmdict.entries

        entries.filter {
            it.hasLanguage(languages)
        }.forEach { entry ->
            val sentences = tatoeba.sentencesForWord(entry.headWord)
            val entryHtml = JapanesePage(outputDocument, languages, entry, sentences).page
            val entryNode = outputDocument.createElement(
                ENTRY_TAG,
                Pair(TITLE_ATTRIBUTE, entry.headWord),
                Pair(ID_ATTRIBUTE, "${Language.JAPANESE.code2.uppercase()}${entry.entrySequence.toString(36)}")
            )

            if (Configuration.DEBUG_OUTPUT_ENTRIES.contains(entry.headWord)) {
                debugOutputList.add(Pair(entry.headWord, entryHtml))
            }

            createIndices(entry, outputDocument).forEach { index ->
                entryNode.appendChild(index)
            }

            entryNode.appendChild(entryHtml)
            documentRoot.appendChild(entryNode)
        }

        languages.forEach { language ->
            nonJapaneseHeadwords(entries, language).forEachIndexed { index, (foreignWord, entries) ->
                val entryHtml = ForeignPage(outputDocument, foreignWord, language, entries).page
                val indexNode = outputDocument.createIndex(foreignWord)
                val entryNode = outputDocument.createElement(
                    ENTRY_TAG,
                    Pair(TITLE_ATTRIBUTE, foreignWord),
                    Pair(ID_ATTRIBUTE, "${language.code2.uppercase()}${index.toString(36)}")
                )

                // Add the page to the debug list (will figure out if they're required to be output later)
                if (Configuration.FOREIGN_DEBUG_OUTPUT_ENTRIES.contains(foreignWord)) {
                    debugOutputList.add(Pair(foreignWord, entryHtml))
                }

                entryNode.appendChild(indexNode)
                entryNode.appendChild(entryHtml)
                documentRoot.appendChild(entryNode)
            }
        }
    }

    private fun createIndices(entry: EntryElement, document: Document): List<Element> {
        return entry.readingElement.flatMap { readingElement ->
            if (!readingElement.noKanji) {
                readingElement.element.flatMap { reading ->
                    listOf(
                        document.createIndex(entry.headWord, null, reading),
                        document.createIndex(reading)
                    )
                }
            } else {
                // If this reading does not directly relate to the headword ignore it
                listOf()
            }
        }
    }

    private fun nonJapaneseHeadwords(entries: List<EntryElement>, language: Language): List<Pair<String, List<EntryElement>>> {
        val result = mutableMapOf<String, MutableList<EntryElement>>()

        entries.forEach { entry ->
            entry.senseElement.forEach { sense ->
                sense.gloss?.filter { gloss ->
                    gloss.language == language && gloss.type == GlossType.OTHER
                }?.map { gloss ->
                    gloss.element
                }?.forEach { word ->
                    result.getOrPut(word) { mutableListOf() }.add(entry)
                }
            }
        }

        return result.toList()
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

    private fun EntryElement.hasLanguage(languages: List<Language>) =
        senseElement.any { sense ->
            sense.gloss?.any { gloss ->
                languages.contains(gloss.language)
            } ?: false
        }

    private fun Element.toOutputString(): String {
        val output = StringWriter()
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, if (Configuration.PRETTY_PRINT_OUTPUT) "yes" else "no")
        transformer.transform(DOMSource(this), StreamResult(output))
        return output.toString()
    }

    override val fileName = "JapaneseDictionary.xml"
    override val subdirectory: String? = null
    override fun data(): String = documentRoot.toOutputString()
    override fun outputFiles(): List<OutputFile> = when (Configuration.DEBUG_OUTPUT) {
        false -> listOf(Makefile, Preferences, PropertyList, Stylesheet, this)
        true -> listOf(Makefile, Preferences, PropertyList, Stylesheet, this) +
            debugOutputList.map { (name, data) -> OutputFileImpl("$name.htm", null, data.toOutputString()) }
    }
}
