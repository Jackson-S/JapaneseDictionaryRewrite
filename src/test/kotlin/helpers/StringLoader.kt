package helpers

import loader.Loader
import java.io.File

class StringLoader(
    val content: String
) : Loader {
    override fun contents(format: Loader.Format): Any =
        when (format) {
            Loader.Format.BYTES -> content.toByteArray()
            Loader.Format.TEXT -> content
        }

    override fun path(): String {
        val tempFile = File.createTempFile("resource", ".tmp")
        tempFile.writeBytes(content.toByteArray())
        tempFile.deleteOnExit() // Ensure the file is deleted at program closure
        return tempFile.path
    }
}
