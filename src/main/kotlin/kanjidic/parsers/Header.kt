package kanjidic.parsers

import kanjidic.datatypes.HeaderElement
import xmlreader.Tag

object Header {
    private const val FILE_VERSION = "file_version"
    private const val DATABASE_VERSION = "database_version"
    private const val DATE = "date_of_creation"

    fun parse(tag: Tag) =
        HeaderElement(
            fileVersion = tag.childrenWithTagName(FILE_VERSION).first().text(),
            databaseVersion = tag.childrenWithTagName(DATABASE_VERSION).first().text(),
            dateOfCreation = tag.childrenWithTagName(DATE).first().text()
        )
}
