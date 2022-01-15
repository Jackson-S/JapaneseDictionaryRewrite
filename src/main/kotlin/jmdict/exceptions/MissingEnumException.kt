package jmdict.exceptions

import java.lang.Exception

class MissingEnumException(
    name: String
) : Exception("Missing enum for '$name'")
