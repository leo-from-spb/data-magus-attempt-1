package lb.kotlin.utils;

import com.google.common.collect.*


public fun<T> ImmutableList<T>.plusAssign(item: T): ImmutableList<T>
{
    val builder = ImmutableList.builder<T>()!!
    builder.addAll(this)
    builder.add(item)
    return builder.build()!!
}