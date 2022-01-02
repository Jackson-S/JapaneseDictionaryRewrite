package loader

import java.io.File
import java.io.FileNotFoundException

class ResourceLoader(
    private val resourcePath: String
) : Loader {
    override fun contents(format: Loader.Format): Any =
        when (format) {
            Loader.Format.BYTES -> resourcePath.asResource().readBytes()
            Loader.Format.TEXT -> resourcePath.asResource().readText()
        }

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
