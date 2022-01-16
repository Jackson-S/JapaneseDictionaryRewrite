package output.common

import java.nio.file.Path

interface OutputFile {
    val fileName: String
    val subdirectory: String?
    fun data(): String
}
