package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.FixFamily

abstract class FixElement : AbElement
{

    final override val id: Int

    constructor(id: Int)
    {
        this.id = id
    }


    abstract override val families: List<FixFamily<FixElement>>


    override fun hashCode() = id
    override fun equals(other: Any?) = this === other // implemented just to mute the warning

    override fun toString() = "$id:${this.javaClass.simpleName}"
}



