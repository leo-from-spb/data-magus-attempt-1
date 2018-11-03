@file:JvmName("NumberFun")
@file:Suppress("UsePropertyAccessSyntax", "unused", "nothing_to_inline", "experimental_unsigned_literals")

package org.jetbrains.datamagus.util

const val `0`: Byte = 0.toByte()
const val `1`: Byte = 1.toByte()
const val `2`: Byte = 2.toByte()
const val `3`: Byte = 3.toByte()
const val `4`: Byte = 4.toByte()
const val `5`: Byte = 5.toByte()
const val `6`: Byte = 6.toByte()
const val `7`: Byte = 7.toByte()
const val `8`: Byte = 8.toByte()
const val `9`: Byte = 9.toByte()


fun Int.toStringPad(width: Int): String {
    var s = this.toString()
    if (s.length < width) s = s.padStart(width)
    return s
}
