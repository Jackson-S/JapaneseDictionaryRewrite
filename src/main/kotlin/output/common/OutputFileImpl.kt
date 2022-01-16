package output.common

data class OutputFileImpl(
    override val fileName: String,
    override val subdirectory: String?,
    private val data: String
): OutputFile {
    override fun data(): String = data
}