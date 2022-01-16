package dataabstraction

import java.io.FileNotFoundException
import java.io.OutputStreamWriter
import java.nio.charset.Charset
import java.nio.file.Path
import kotlin.io.path.Path

class FileIOInterface(
    private val filePath: Path
) : IOInterface {
    constructor(filePath: String) : this(toPath(filePath))

    private companion object {
        fun toPath(string: String): Path {
            return if (string.startsWith("~")) {
                // Substitute the "~" with the user home
                Path(System.getProperty("user.home"), string.removePrefix("~"))
            } else {
                Path(string)
            }
        }
    }

    override fun readContents(encoding: Charset): String {
        val fileHandle = filePath.toFile()

        if (!fileHandle.exists()) {
            throw FileNotFoundException()
        }

        return fileHandle.readText(charset = encoding)
    }

    override fun write(data: String, encoding: Charset) {
        val fileHandle = filePath.toFile()
        val fileWriter = OutputStreamWriter(fileHandle.outputStream(), encoding)
        fileWriter.write(data)
        fileWriter.close()
    }

    override fun path() = filePath
}
