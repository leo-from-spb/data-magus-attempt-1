package org.jetbrains.datamagus.util


open class ValueConversionException(message: String) : RuntimeException(message)

class NumberConversionException(message: String) : ValueConversionException(message)
