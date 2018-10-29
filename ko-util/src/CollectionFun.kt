@file:JvmName("CollectionFun")

package org.jetbrains.datamagus.util


/**
 * Finds the given value in the specified range.
 */
fun<E> Array<E>.indexOf(value: E, from: Int = 0, till: Int = this.size, notFound: Int = -1): Int {
    val n = this.size
    val min = if (from >= 0) from else 0
    val max = if (till <= n) till else n
    if (min >= max) return notFound
    for (i in from until till) if (this[i] == value) return i
    return notFound
}

/**
 * Finds the given value in the specified range.
 */
fun<E> List<E>.indexOf(value: E, from: Int = 0, till: Int = this.size, notFound: Int = -1): Int {
    val n = this.size
    val min = if (from >= 0) from else 0
    val max = if (till <= n) till else n
    if (min >= max) return notFound
    for (i in from until till) if (this[i] == value) return i
    return notFound
}

/**
 * Finds the given value in the specified range.
 */
fun<E> Array<E>.indexOf(from: Int = 0, till: Int = this.size, notFound: Int = -1, predicate: (E) -> Boolean): Int {
    val n = this.size
    val min = if (from >= 0) from else 0
    val max = if (till <= n) till else n
    if (min >= max) return notFound
    for (i in from until till) if (predicate(this[i])) return i
    return notFound
}

/**
 * Finds the given value in the specified range.
 */
fun<E> List<E>.indexOf(from: Int = 0, till: Int = this.size, notFound: Int = -1, predicate: (E) -> Boolean): Int {
    val n = this.size
    val min = if (from >= 0) from else 0
    val max = if (till <= n) till else n
    if (min >= max) return notFound
    for (i in from until till) if (predicate(this[i])) return i
    return notFound
}

