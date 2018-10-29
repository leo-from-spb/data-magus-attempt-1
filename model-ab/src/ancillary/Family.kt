package org.jetbrains.datamagus.model.ancillary

import org.jetbrains.datamagus.model.content.AbElement
import org.jetbrains.datamagus.model.content.AbNamedElement

interface Family<out E: AbElement> : Collection<E>
{
    operator fun get(index: Int): E
    val first: E
    val last: E
    override val size: Int
    override fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean
}


fun <E: AbNamedElement> Family<E>.indexOfName(name: String, caseSensitive: Boolean = false): Int  {
    if (this.isEmpty()) return -1
    for ((index, element) in this.withIndex())
    {
        val elementName = element.name
        if (elementName != null && elementName.equals(name, !caseSensitive)) return index
    }
    return -1
}

fun <E: AbNamedElement> Family<E>.findByName(name: String, caseSensitive: Boolean = false): E?  {
    if (this.isEmpty()) return null
    for (element in this)
    {
        val elementName = element.name
        if (elementName != null && elementName.equals(name, !caseSensitive)) return element
    }
    return null
}
