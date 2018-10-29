package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.model.content.AbElement
import kotlin.reflect.KClass

sealed class MetaEntity
{
    val nameCap: String
    val nameDec: String


    constructor(name: String)
    {
        this.nameCap = name.capitalize()
        this.nameDec = name.decapitalize()
    }
}


class MetaNode : MetaEntity
{
    val primaryInterface: KClass<out AbElement>
    var isFinal = false

    constructor(primaryInterface: KClass<out AbElement>, isFinal: Boolean) : super(primaryInterface.simpleName!!)
    {
        this.primaryInterface = primaryInterface
        this.isFinal = isFinal
    }
}