package com.jetbrains.datamagus.model.ancillary

import com.jetbrains.datamagus.model.content.AbElement

interface Family<out E: AbElement> : Collection<E>
{
    
    override val size: Int
    override fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean

}

