package loader

interface Loader {
    enum class Format {
        BYTES,
        TEXT
    }

    /**
     * Reads the contents of a file in either [Format.BYTES] or [Format.TEXT], if [Format.TEXT] is selected the returned
     * type will be a UTF-8 [String], if [Format.BYTES] is selected then the return type will be [ByteArray]
     */
    fun contents(format: Format): Any

    /**
     * Returns the path of the file. The path returned may only be temporary
     */
    fun path(): String
}
