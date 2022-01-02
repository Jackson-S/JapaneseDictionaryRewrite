package sentences

class Mecab {
    private val runtime = Runtime.getRuntime()
    private val process = runtime.exec("mecab")

    fun tokenizeSentence(sentence: String): String {
        val outputStreamWriter = process.outputStream.writer()
        val inputStreamReader = process.inputStream.reader().buffered()

        outputStreamWriter.write(sentence)
        outputStreamWriter.write("\n")
        outputStreamWriter.flush()

        val response = mutableListOf<String>()

        while (true) {
            val nextLine = inputStreamReader.readLine()

            if (nextLine == "EOS") {
                break
            }

            response.add(nextLine)
        }

        return response.joinToString("\n")
    }

    private fun hasEnded(response: MutableList<Byte>): Boolean {
        if (response.size < 4) return false
        return response.subList(response.size - 4, response.size) == "\nEOS".toByteArray().toList()
    }
}
