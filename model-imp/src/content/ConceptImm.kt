package org.jetbrains.datamagus.model.content

import com.jetbrains.datamagus.model.content.*
import org.jetbrains.datamagus.model.ancillary.EmptyFamily
import org.jetbrains.datamagus.model.ancillary.ImmFamily

class ImmConModel : ImmElement, ConModel
{

    constructor(id: Int,
                subAreas: ImmFamily<ImmConSubArea> = EmptyFamily,
                domains: ImmFamily<ImmConDomain> = EmptyFamily,
                entities: ImmFamily<ImmConEntity> = EmptyFamily,
                name: String? = null) : super(id)
    {
        this.subAreas = subAreas
        this.domains = domains
        this.entities = entities
        this.name = name
    }

    override val subAreas: ImmFamily<ImmConSubArea>
    override val domains: ImmFamily<ImmConDomain>
    override val entities: ImmFamily<ImmConEntity>

    override val name: String?

}


class ImmConSubArea : ImmElement, ConSubArea
{

    override val domains: ImmFamily<ImmConDomain>
    override val entities: ImmFamily<ImmConEntity>

    override val name: String?

    constructor(id: Int,
                domains: ImmFamily<ImmConDomain> = EmptyFamily,
                entities: ImmFamily<ImmConEntity> = EmptyFamily,
                name: String? = null) : super(id)
    {
        this.domains = domains
        this.entities = entities
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
