package interfaces

import java.nio.charset.Charset
import java.nio.file.Path

interface IOInterface {
    /**
     * Reads the contents of a file in the encoding specified, the resulting string will be in UTF-8
     */
    fun readContents(encoding: Charset = Charsets.UTF_8): String

    /**
     * Write to the contents of the file or other data source of the IO interface
     */
    fun write(data: String, encoding: Charset = Charsets.UTF_8)

    /**
     * Returns the path of the file. The path returned may only be temporary
     */
    fun path(): Path
}
