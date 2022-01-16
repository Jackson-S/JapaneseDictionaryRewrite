package kanjidic

import kanjidic.datatypes.CharacterElement
import kanjidic.datatypes.HeaderElement
import kanjidic.parsers.Character
import kanjidic.parsers.Header
import dataabstraction.IOInterface
import dataabstraction.NullIOInterface
import xmlreader.impl.TagImpl

class KanjiDic2(
    IOInterface: IOInterface
) {
    private val header: HeaderElement?
    val entries: List<CharacterElement>

    init {
        when (IOInterface) {
            is NullIOInterface -> {
                header = null
                entries = emptyList()
            }
            else -> {
                val root = TagImpl(IOInterface, "kanjidic2")
                header = Header.parse(root.childrenWithTagName("header").first())
                entries = root.childrenWithTagName("character").map { Character.parse(it) }
            }
        }
    }

    fun entry(headword: String) = entries.first { it.literal == headword }

    override fun toString(): String = entries.joinToString { it.toString() }
}
