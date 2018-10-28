package org.jetbrains.datamagus.model.content

import com.jetbrains.datamagus.model.content.*

class ImmConModel : ImmElement, ConModel
{

    override val id: Int
    override val name: String?

    constructor(id: Int, name: String? = null) : super (id)
    {
        this.id = id
        this.name = name
    }
}


class ImmConSubArea : ImmElement, ConSubArea
{

    override val id: Int
    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.id = id
        this.name = name
    }

}


class ImmConDomain : ImmElement, ConDomain
{

    override val id: Int
    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.id = id
        this.name = name
    }

}


class ImmConEntity : ImmElement, ConEntity
{

    override val id: Int
    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.id = id
        this.name = name
    }

}


class ImmConAttribute : ImmElement, ConAttribute
{

    override val id: Int
    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.id = id
        this.name = name
    }

}
