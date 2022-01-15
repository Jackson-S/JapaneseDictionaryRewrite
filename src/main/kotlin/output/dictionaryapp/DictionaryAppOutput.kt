package output.dictionaryapp

import Configuration
import common.Language
import jmdict.JMDict
import jmdict.datatypes.EntryElement
import kanjidic.KanjiDic2
import org.w3c.dom.Document
import org.w3c.dom.Element
import output.dictionaryapp.templates.auxiliary.Makefile
import output.dictionaryapp.templates.auxiliary.PropertyList
import output.dictionaryapp.templates.auxiliary.Stylesheet
import output.dictionaryapp.templates.foreign.ForeignPage
import output.dictionaryapp.templates.japanese.JapanesePage
import sentences.TatoebaSentences
import java.io.FileWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class DictionaryAppOutput(
    jmdict: JMDict,
    kanjiDic2: KanjiDic2,
    private val tatoeba: TatoebaSentences,
    private val languages: List<Language>
) {
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

            if (Configuration.DEBUG_OUTPUT && Configuration.DEBUG_OUTPUT_ENTRIES.contains(entry.headWord)) {
                writePage(Configuration.OUTPUT_DIRECTORY, entry.headWord, entryHtml)
            }

            createIndices(entry, outputDocument).forEach { index ->
                entryNode.appendChild(index)
            }

            entryNode.appendChild(entryHtml)
            documentRoot.appendChild(entryNode)
        }

        languages.forEach { language ->
            nonJapaneseHeadwords(entries, language).forEachIndexed { index, (foreignWord, entries) ->
                val entryHtml = ForeignPage(outputDocument, foreignWord, entries).page
                val indexNode = outputDocument.createIndex(foreignWord)
                val entryNode = outputDocument.createElement(
                    ENTRY_TAG,
                    Pair(TITLE_ATTRIBUTE, foreignWord),
                    Pair(ID_ATTRIBUTE, "${language.code2.uppercase()}${index.toString(36)}")
                )

                if (Configuration.DEBUG_OUTPUT && Configuration.FOREIGN_DEBUG_OUTPUT_ENTRIES.contains(foreignWord)) {
                    writePage(Configuration.OUTPUT_DIRECTORY, foreignWord, entryHtml)
                }

                entryNode.appendChild(indexNode)
                entryNode.appendChild(entryHtml)
                documentRoot.appendChild(entryNode)
            }
        }
    }

    fun writeAll(outputDirectory: String) {
        writeStylesheet(outputDirectory)
        writeMakefile(outputDirectory)
        writePropertyList(outputDirectory)
        writeDictionary(outputDirectory)
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
                    gloss.language == language
                }?.map { gloss ->
                    gloss.element.replace("\\(.*\\)", "")
                }?.filter { baseWordOrPhrase ->
                    baseWordOrPhrase.split(" ").size < 3
                }?.forEach { word ->
                    result.getOrPut(word) { mutableListOf() }.add(entry)
                }
            }
        }

        return result.toList()
    }

    private fun Document.createIndex(value: String, title: String? = null, yomi: String? = null): Element {
        val attributes = listOf(
            Pair(VALUE_ATTRIBUTE, value),
            title?.let { Pair(TITLE_ATTRIBUTE, it) },
            yomi?.let { Pair(YOMI_ATTRIBUTE, yomi) }
        ).filterNotNull().toTypedArray()

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

    private fun writePage(outputDirectory: String, word: String, page: Element) {
        val outputWriter = FileWriter("$outputDirectory/$word.html")
        val outputResult = StreamResult(outputWriter)
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, if (Configuration.PRETTY_PRINT_OUTPUT) "yes" else "no")
        transformer.transform(DOMSource(page), outputResult)
        outputWriter.close()
    }

    private fun writeDictionary(outputDirectory: String) {
        val outputWriter = FileWriter("$outputDirectory/JapaneseDictionary.xml")
        val outputResult = StreamResult(outputWriter)
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, if (Configuration.PRETTY_PRINT_OUTPUT) "yes" else "no")
        transformer.transform(DOMSource(documentRoot), outputResult)
        outputWriter.close()
    }

    private fun writeStylesheet(outputDirectory: String) {
        val outputWriter = FileWriter("$outputDirectory/JapaneseDictionary.css")
        outputWriter.write(Stylesheet.stylesheet())
        outputWriter.close()
    }

    private fun writeMakefile(outputDirectory: String) {
        val outputWriter = FileWriter("$outputDirectory/Makefile")
        outputWriter.write(Makefile.makefile())
        outputWriter.close()
    }

    private fun writePropertyList(outputDirectory: String) {
        val outputWriter = FileWriter("$outputDirectory/JapaneseDictionary.plist")
        outputWriter.write(PropertyList.propertList())
        outputWriter.close()
    }
}
