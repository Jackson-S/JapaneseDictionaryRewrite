package xmlreader.impl

import dataabstraction.IOInterface
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import xmlreader.Tag
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class TagImpl : Tag {
    private val root: Element
    private val parent: Tag?

    constructor(IOInterface: IOInterface, rootTag: String) {
        val xmlFile = IOInterface.path().toFile()
        val xmlInput = InputSource(StringReader(xmlFile.readText()))
        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlInput)
        root = document.getElementsByTagName(rootTag).first() as Element
        parent = null
    }

    private constructor(element: Element, elementParent: Tag) {
        parent = elementParent
        root = element
    }

    override fun properties(): Map<String, String> =
        (0 until root.attributes.length).associate {
            root.attributes.item(it).nodeName to root.attributes.item(it).textContent
        }

    override fun text(): String = this.root.textContent

    override fun children(): List<Tag> =
        root.childNodes.map {
            val element = it as? Element

            if (element == null) {
                null
            } else {
                TagImpl(element, this)
            }
        }.filterNotNull()

    override fun childrenWithTagName(name: String): List<Tag> =
        root.getElementsByTagName(name).map {
            TagImpl(it as Element, this)
        }

    private inline fun <R> NodeList.map(action: (Node) -> R) = (0 until this.length).map { action(this.item(it)) }

    private fun NodeList.first() = if (this.length != 0) this.item(0) else null
}
