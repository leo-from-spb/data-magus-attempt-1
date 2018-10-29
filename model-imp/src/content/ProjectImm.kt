package org.jetbrains.datamagus.model.content


class ImmProProject : ProProject
{

    override val id: Int
    override val version: Int
    override val name: String

    constructor(id: Int, version: Int, name: String)
    {
        this.version = version
        this.name = name
        this.id = id
    }

}