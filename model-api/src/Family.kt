package org.jetbrains.datamagus.model.org.jetbrains.datamagus.model

import org.jetbrains.datamagus.model.content.AbElement

interface Family<out E: AbElement> : Collection<E>
{
    
    override val size: Int
    override fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean

}

