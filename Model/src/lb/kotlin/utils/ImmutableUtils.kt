package lb.kotlin.utils;

import com.google.common.collect.*


public fun<T> emptyList(): List<T>
{
    return ImmutableList.of<T>()!!
}


public fun<T> ImmutableList<T>.plus(item: T): ImmutableList<T>
{
    val builder = ImmutableList.builder<T>()!!
    builder.addAll(this)
    builder.add(item)
    return builder.build()!!
}