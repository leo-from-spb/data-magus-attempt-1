@file:JvmName("TextFun")

package org.jetbrains.datamagus.util


infix fun CharSequence?.prefixed(prefix: String): Boolean = this != null && this.startsWith(prefix)
infix fun CharSequence?.suffixed(suffix: String): Boolean = this != null && this.endsWith(suffix)


fun<E> Iterable<E>.toText(format: E.() -> String): CharSequence {
    val b = StringBuilder()
    for (e in this)
    {
        if (b.isNotEmpty()) b.append('\n')
        b.append(e.format())
    }
    return b
}
