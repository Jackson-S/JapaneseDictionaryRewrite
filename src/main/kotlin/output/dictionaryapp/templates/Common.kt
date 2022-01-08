package output.dictionaryapp.templates

internal fun <T> List<T>.exceptFirst() = subList(1, size)

internal fun <T> List<T>.isIndexOfFinalElement(index: Int) = size - 1 == index

internal fun <T> List<T>.first(limit: Int) = if (size >= limit) subList(0, limit - 1) else this
