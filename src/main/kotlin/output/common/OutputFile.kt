package output.common

interface OutputFile {
    val fileName: String
    val subdirectory: String?
    fun data(): String
}
