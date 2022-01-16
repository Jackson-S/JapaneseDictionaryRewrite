package output.common.exceptions

class MissingEnumConversion(enum: Any) : Exception("Missing conversion for $enum")
