package org.jetbrains.datamagus.model.content

import com.jetbrains.datamagus.model.content.*

class ImmConModel : ImmElement, ConModel
{

    override val name: String?

    constructor(id: Int, name: String? = null) : super (id)
    {
        this.name = name
    }
}


class ImmConSubArea : ImmElement, ConSubArea
{

    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.name = name
    }

}


class ImmConDomain : ImmElement, ConDomain
{

    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.name = name
    }

}


class ImmConEntity : ImmElement, ConEntity
{

    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.name = name
    }

}


class ImmConAttribute : ImmElement, ConAttribute
{

    override val name: String?

    constructor(id: Int, name: String? = null) : super(id)
    {
        this.name = name
    }

}
