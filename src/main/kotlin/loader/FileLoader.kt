package loader

import java.io.File
import java.io.FileNotFoundException
import java.nio.charset.Charset

class FileLoader(
    private val filePath: String
) : Loader {
    override fun contentsAsText(encoding: Charset): String {
        val fileHandle = File(filePath)

        if (!fileHandle.exists()) {
            throw FileNotFoundException()
        }

        return fileHandle.readText(charset = encoding)
    }

    override fun contentsAsBytes(): ByteArray {
        val fileHandle = File(filePath)

        if (!fileHandle.exists()) {
            throw FileNotFoundException()
        }

        return fileHandle.readBytes()
    }

    override fun path(): String = filePath
}
