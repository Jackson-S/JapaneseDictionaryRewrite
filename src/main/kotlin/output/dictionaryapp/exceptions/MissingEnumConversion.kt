package output.dictionaryapp.exceptions

class MissingEnumConversion(enum: Any) : Exception("Missing conversion for $enum")
