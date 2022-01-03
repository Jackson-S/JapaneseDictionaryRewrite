package loader

import java.nio.charset.Charset

interface Loader {
    enum class Format {
        BYTES,
        TEXT
    }

    /**
     * Reads the contents of a file in the encoding specified, the resulting string will be in UTF-8
     */
    fun contentsAsText(encoding: Charset = Charsets.UTF_8): String

    /**
     * Reads the contents of a file as an array of bytes
     */
    fun contentsAsBytes(): ByteArray

    /**
     * Returns the path of the file. The path returned may only be temporary
     */
    fun path(): String
}
