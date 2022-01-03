package xmlreader

interface Tag {
    fun properties(): Map<String, String>

    fun text(): String

    fun children(): List<Tag>

    fun childrenWithTagName(name: String): List<Tag>
}