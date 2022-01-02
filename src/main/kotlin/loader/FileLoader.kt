package loader

import java.io.File
import java.io.FileNotFoundException

class FileLoader(
    private val filePath: String
) : Loader {
    override fun contents(format: Loader.Format): Any {
        val fileHandle = File(filePath)

        if (!fileHandle.exists()) {
            throw FileNotFoundException()
        }

        return when (format) {
            Loader.Format.BYTES -> fileHandle.readBytes()
            Loader.Format.TEXT -> fileHandle.readText(charset = Charsets.UTF_8)
        }
    }

    override fun path(): String = filePath
}
