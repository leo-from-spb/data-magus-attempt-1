@file:JvmName("TextFun")

package org.jetbrains.datamagus.util


infix fun CharSequence?.prefixed(prefix: String): Boolean = this != null && this.startsWith(prefix)
infix fun CharSequence?.suffixed(suffix: String): Boolean = this != null && this.endsWith(suffix)


fun CharSequence.splitByFirstCap(): Pair<String, String> {
    val n = this.length
    val p = this.indexOfCap(from = 1, notFound = n)
    return Pair(this.substring(0, p), this.substring(p, n))
}

fun CharSequence.indexOfCap(from: Int = 0, till: Int = this.length, notFound: Int = -1): Int {
    for (i in from .. till) if (this[i].isUpperCase()) return i
    return notFound
}



fun<E> Iterable<E>.toText(delimiter: String = "\n", format: E.() -> String): CharSequence {
    val b = StringBuilder()
    for (e in this)
    {
        if (b.isNotEmpty()) b.append(delimiter)
        b.append(e.format())
    }
    return b
}
