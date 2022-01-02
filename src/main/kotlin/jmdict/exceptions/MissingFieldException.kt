package jmdict.exceptions

import java.lang.Exception

class MissingFieldException(
    fieldName: String
) : Exception("Missing field name: $fieldName")
