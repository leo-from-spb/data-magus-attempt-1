@file:JvmName("BooleanFun")
@file:Suppress("UsePropertyAccessSyntax", "unused", "nothing_to_inline")

package org.jetbrains.datamagus.util


inline val Boolean.byte: Byte get() = if (this) 1.toByte() else 0.toByte()

inline val Boolean.int: Int get() = if (this) 1 else 0


/**
 * Returns the first argument when [this] is true and the second one otherwise.
 * A kind of lite replacement of the ternary operator.
 * @param onTrue what to return when [this] this true.
 * @param onFalse what to return when [this] this false.
 */
inline fun<V> Boolean.choose(onTrue: V, onFalse: V) = if (this) onTrue else onFalse

/**
 * Returns the argument when [this] is true and null otherwise.
 */
inline operator fun<V> Boolean.rem(value: V?): V? = if (this) value else null
