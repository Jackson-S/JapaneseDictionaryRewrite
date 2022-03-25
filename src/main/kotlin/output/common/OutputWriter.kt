package output.common

import interfaces.FileIOInterface
import java.nio.file.InvalidPathException
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

class OutputWriter {
    fun writeAll(outputDirectory: String, output: Output) {
        val files = output.outputFiles()
        val outputDirectoryPath = FileIOInterface(outputDirectory).path()

        if (!outputDirectoryPath.parent.exists()) {
            throw InvalidPathException(
                Path(outputDirectory).parent.toString(),
                "Build path must be located in existing directory"
            )
        }

        if (!outputDirectoryPath.exists()) {
            outputDirectoryPath.createDirectory()
        }

        files.forEach { fileInfo ->
            // Create the subdirectory if it doesn't exist
            val fileOutputPath = fileInfo.subdirectory?.let {
                val outputSubdirectory = Path(outputDirectoryPath.toString(), it)
                if (!outputSubdirectory.exists()) {
                    outputSubdirectory.createDirectories()
                }
                outputSubdirectory
            } ?: outputDirectoryPath

            val outputFilePath = Path(fileOutputPath.toString(), fileInfo.fileName)

            val outputFile = FileIOInterface(outputFilePath)
            outputFile.write(fileInfo.data())
        }
    }
}
