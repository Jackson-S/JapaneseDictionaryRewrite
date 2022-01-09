package output.dictionaryapp

import common.Language
import jmdict.JMDict
import jmdict.datatypes.EntryElement
import kanjidic.KanjiDic2
import org.w3c.dom.Document
import output.dictionaryapp.templates.JMDictPage
import output.dictionaryapp.templates.Makefile
import output.dictionaryapp.templates.PropertyList
import output.dictionaryapp.templates.Stylesheet
import sentences.TatoebaSentences
import java.io.FileWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class DictionaryAppOutput(
    val jmdict: JMDict,
    val kanjiDic2: KanjiDic2,
    val tatoeba: TatoebaSentences,
    val languages: List<Language>
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
        val jmDictPageGenerator = JMDictPage(outputDocument, languages)

        documentRoot.setAttribute("xmlns:d", DICTIONARY_NAMESPACE_URI)

        val entries = if (Configuration.DEBUG_OUTPUT)
            jmdict.entries.filter { Configuration.DEBUG_OUTPUT_ENTRIES.contains(it.headWord) }
        else
            jmdict.entries

        entries.filter {
            it.hasLanguage(languages)
        }.forEach { entry ->
            val sentences = tatoeba.sentencesForWord(entry.headWord)
            val entryHtml = jmDictPageGenerator.japaneseEntry(entry, sentences)

            val entryNode = outputDocument.createElement(ENTRY_TAG)
            entryNode.setAttribute(TITLE_ATTRIBUTE, entry.headWord)
            entryNode.setAttribute(ID_ATTRIBUTE, "J${entry.entrySequence.toString(36)}")

            createIndicies(entry, outputDocument).forEach { index ->
                entryNode.appendChild(index)
            }

            entryNode.appendChild(entryHtml)
            documentRoot.appendChild(entryNode)
        }

        Configuration.LANGUAGE.forEach { language ->
            nonJapaneseHeadwords(entries, language).forEach { (foreignWord, entries) ->
                val entryHtml = jmDictPageGenerator.englishEntry(foreignWord, entries)
                val indexNode = outputDocument.createElement(INDEX_TAG)
                indexNode.setAttribute(VALUE_ATTRIBUTE, foreignWord)
                indexNode.setAttribute(TITLE_ATTRIBUTE, foreignWord)

                val entryNode = outputDocument.createElement(ENTRY_TAG)
                entryNode.setAttribute(TITLE_ATTRIBUTE, foreignWord)
                entryNode.setAttribute(ID_ATTRIBUTE, "F${language.code2}$foreignWord")

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

    private fun createIndicies(entry: EntryElement, document: Document) =
        entry.readingElement.map {
            val indexNode = document.createElement(INDEX_TAG)
            indexNode.setAttribute(VALUE_ATTRIBUTE, entry.headWord)
            indexNode.setAttribute(TITLE_ATTRIBUTE, entry.headWord)
            entry.headReading?.let { indexNode.setAttribute(YOMI_ATTRIBUTE, it) }
            indexNode
        }

    private fun nonJapaneseHeadwords(entries: List<EntryElement>, language: Language): Map<String, List<EntryElement>> {
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

        return result
    }

    private fun EntryElement.hasLanguage(languages: List<Language>) =
        senseElement.any { sense ->
            sense.gloss?.any { gloss ->
                languages.contains(gloss.language)
            } ?: false
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
