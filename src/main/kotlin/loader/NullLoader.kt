package loader

import java.nio.charset.Charset

/**
 * Returns empty responses when called upon
 */
class NullLoader : Loader {
    override fun contentsAsText(encoding: Charset) = ""

    override fun contentsAsBytes(): ByteArray = byteArrayOf()

    override fun path(): String = ""
}
