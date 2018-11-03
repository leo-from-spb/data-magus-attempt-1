package org.jetbrains.datamagus.model.content

abstract class FixElement : AbElement
{

    final override val id: Int

    constructor(id: Int)
    {
        this.id = id
    }

    override fun hashCode() = id
    override fun equals(other: Any?) = this === other // implemented just to mute the warning

    override fun toString() = "$id:${this.javaClass.simpleName}"
}



