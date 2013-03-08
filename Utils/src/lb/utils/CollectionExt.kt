package lb.utils

import com.google.common.collect.ImmutableMap

fun<K,V> Collection<V>.toMap(key: (obj:V) -> (K)): Map<K,V>
{
    val mapBuilder = ImmutableMap.builder<K,V>()!!
    for (obj in this)
        mapBuilder.put(key(obj), obj)
    return mapBuilder.build()!!
}

fun<O,K,V> Collection<O>.toMap(key: (obj:O) -> (K), value: (obj:O) -> (V)): Map<K,V>
{
    val mapBuilder = ImmutableMap.builder<K,V>()!!
    for (obj in this)
        mapBuilder.put(key(obj), value(obj))
    return mapBuilder.build()!!
}


public fun<T> Collection<T>.toStr(delimiter: String = ",",
                                  prefix: String = "",
                                  sufix: String = "",
                                  empty: String = "") : String
{
    if (this.isEmpty())
        return empty
    val b = StringBuilder()
    b.append(prefix)
    var first = true
    for (item in this) {
        if (first)
            first = false
        else
            b.append(delimiter)
        b.append(item)
    }
    b.append(sufix)
    return b.toString()
}