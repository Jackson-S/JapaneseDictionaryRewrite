package loader

import java.io.File
import java.io.FileNotFoundException
import java.nio.charset.Charset

class ResourceLoader(
    private val resourcePath: String
) : Loader {
    override fun contentsAsText(encoding: Charset): String =
        resourcePath.asResource().readText()

    override fun contentsAsBytes(): ByteArray =
        resourcePath.asResource().readBytes()

    override fun path(): String {
        val fileContents = resourcePath.asResource().readBytes()
        val tempFile = File.createTempFile("resource", ".tmp")
        tempFile.writeBytes(fileContents)
        tempFile.deleteOnExit() // Ensure the file is deleted at program closure
        return tempFile.path
    }

    private fun String.asResource() =
        this::class.java.getResource(this) ?: throw FileNotFoundException("Unable to find resource $this")
}
