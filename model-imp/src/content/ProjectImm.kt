package org.jetbrains.datamagus.model.content

import com.jetbrains.datamagus.model.content.ProProject


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