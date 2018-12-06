@file:JvmName("ChangeFun")
@file:Suppress("UsePropertyAccessSyntax", "nothing_to_inline")

package org.jetbrains.datamagus.util



inline class ByteChangeValue (val _value_: Short) 
inline infix fun Byte.change(what: Byte) = ByteChangeValue(if (this == what) Short.MIN_VALUE else this.toShort())
inline infix fun ByteChangeValue.with(replacement: Byte): Byte = if (this._value_ == Short.MIN_VALUE) replacement else this._value_.toByte()


inline class ShortChangeValue (val _value_: Int)
inline infix fun Short.change(what: Short) = ShortChangeValue(if (this == what) Int.MIN_VALUE else this.toInt())
inline infix fun ShortChangeValue.with(replacement: Short): Short = if (this._value_ == Int.MIN_VALUE) replacement else this._value_.toShort()


inline class IntChangeValue (val _value_: Long)
inline infix fun Int.change(what: Int) = IntChangeValue(if (this == what) Long.MIN_VALUE else this.toLong())
inline infix fun IntChangeValue.with(replacement: Int): Int = if (this._value_ == Long.MIN_VALUE) replacement else this._value_.toInt()


inline class LongChangeValue (val _value_: Long?)
inline infix fun Long.change(what: Long) = LongChangeValue(if (this == what) null else this)
inline infix fun LongChangeValue.with(replacement: Long): Long = this._value_ ?: replacement


inline class CharChangeValue (val _value_: Int)
inline infix fun Char.change(what: Char) = CharChangeValue(if (this == what) Int.MIN_VALUE else this.toInt())
inline infix fun CharChangeValue.with(replacement: Char): Char = if (this._value_ == Int.MIN_VALUE) replacement else this._value_.toChar()


inline class AnyChangeValue (val _value_: Any?)
inline infix fun Any.change(what: Any) = AnyChangeValue(if (this == what) null else this)
inline infix fun AnyChangeValue.with(replacement: Any): Any = this._value_ ?: replacement


