@file:JvmName("ArrayFun")

package org.jetbrains.datamagus.util.org.jetbrains.datamagus.util

import org.jetbrains.datamagus.util.NumberConversionException


/**
 * Converts array of ints into an array of bytes.
 * When one of values doesn't fit into a byte, a [NumberConversionException] is thrown.
 */
fun IntArray.toBytes(): ByteArray {
    val n = this.size
    val bytes = ByteArray(n)
    for (i in 0 until n) {
        val v = this[i]
        if (v >= Byte.MIN_VALUE && v <= Byte.MAX_VALUE) bytes[i] = v.toByte()
        else throw NumberConversionException("Cannot convert integer value $v into a byte")
    }
    return bytes
}


/**
 * Converts array of ints into an array of shorts.
 * When one of values doesn't fit into a short, a [NumberConversionException] is thrown.
 */
fun IntArray.toShorts(): ShortArray {
    val n = this.size
    val shorts = ShortArray(n)
    for (i in 0 until n) {
        val v = this[i]
        if (v >= Short.MIN_VALUE && v <= Short.MAX_VALUE) shorts[i] = v.toShort()
        else throw NumberConversionException("Cannot convert integer value $v into a short")
    }
    return shorts
}

