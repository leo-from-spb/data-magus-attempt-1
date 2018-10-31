package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.EmptyFamily
import org.jetbrains.datamagus.model.ancillary.ImmFamily

class ImmConModel : ImmElement, ConModel
{

    constructor(id: Int,
                subAreas: ImmFamily<ImmConSubArea> = EmptyFamily,
                domains: ImmFamily<ImmConDomain> = EmptyFamily,
                entities: ImmFamily<ImmConEntity> = EmptyFamily,
                abb: String? = null,
                name: String? = null) : super(id)
    {
        this.subAreas = subAreas
        this.domains = domains
        this.entities = entities
        this.abb = abb
        this.name = name
    }

    override val subAreas: ImmFamily<ImmConSubArea>
    override val domains: ImmFamily<ImmConDomain>
    override val entities: ImmFamily<ImmConEntity>

    override val abb: String?
    override val name: String?

}


class ImmConSubArea : ImmElement, ConSubArea
{

    override val domains: ImmFamily<ImmConDomain>
    override val entities: ImmFamily<ImmConEntity>

    override val abb: String?
    override val name: String?

    constructor(id: Int,
                domains: ImmFamily<ImmConDomain> = EmptyFamily,
                entities: ImmFamily<ImmConEntity> = EmptyFamily,
                abb: String? = null,
                name: String? = null) : super(id)
    {
        this.domains = domains
        this.entities = entities
        this.abb = abb
        this.name = name
    }
}


class ImmConDomain : ImmElement, ConDomain
{

    override val abb: String?
    override val name: String?
    override val surrogate: Boolean

    constructor(id: Int,
                abb: String? = null,
                name: String? = null,
                surrogate: Boolean = false
                ) : super(id)
    {
        this.abb = abb
        this.name = name
        this.surrogate = surrogate
    }

}


class ImmConEntity : ImmElement, ConEntity
{

    override val abb: String?
    override val name: String?

    constructor(id: Int,
                abb: String? = null,
                name: String? = null) : super(id)
    {
        this.abb = abb
        this.name = name
    }

}


class ImmConAttribute : ImmElement, ConAttribute
{

    override val abb: String?
    override val name: String?
    override val surrogate: Boolean

    constructor(id: Int,
                abb: String? = null,
                name: String? = null,
                surrogate: Boolean = false
    )
            : super(id)
    {
        this.abb = abb
        this.name = name
        this.surrogate = surrogate
    }

}
