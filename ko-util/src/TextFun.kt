@file:JvmName("TextFun")

package org.jetbrains.datamagus.util


infix fun CharSequence?.prefixed(prefix: String): Boolean = this != null && this.startsWith(prefix)
infix fun CharSequence?.suffixed(suffix: String): Boolean = this != null && this.endsWith(suffix)

infix fun String.ensurePrefix(prefix: String): String = if (this prefixed prefix) this else prefix + this
infix fun String.ensureSuffix(suffix: String): String = if (this suffixed suffix) this else this + suffix


infix fun CharSequence.pad(width: Int) = this.padEnd(width)


fun CharSequence.splitByFirstCap(): Pair<String, String> {
    val n = this.length
    val p = this.indexOfCap(from = 1, notFound = n)
    return Pair(this.substring(0, p), this.substring(p, n))
}

fun CharSequence.indexOfCap(from: Int = 0, till: Int = this.length, notFound: Int = -1): Int {
    for (i in from .. till) if (this[i].isUpperCase()) return i
    return notFound
}



fun<E> Iterable<E>.toText(delimiter: String = "\n",
                          prefix: String = "",
                          suffix: String = "",
                          empty: String? = null,
                          //single: String? = null,
                          format: E.() -> String): CharSequence {
    var n = 0
    val b = StringBuilder()
    for (e in this)
    {
        b.append(if (n == 0) prefix else delimiter)
        b.append(e.format())
        n++
    }
    when (n) {
        0 -> if (empty != null) return empty
        //1 -> if (single != null) return single
    }
    b.append(suffix)
    return b
}


fun<E> Iterable<E>.toStrings(format: E.() -> String): List<String> {
    val list = mutableListOf<String>()
    for (e in this)
        list += e.format()
    return list
}

