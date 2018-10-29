@file:JvmName("NumberFun")

@file:Suppress("UsePropertyAccessSyntax", "unused")
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

inline val Boolean.byte: Byte get() = if (this) `1` else `0`
inline val Boolean.int: Int get() = if (this) 1 else 0

inline fun<E> Boolean.choose(onTrue: E, onFalse: E) = if (this) onTrue else onFalse
