package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.model.content.AbElement
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

sealed class MetaMatter
{
    val nameCap: String
    val nameDec: String


    constructor(name: String)
    {
        this.nameCap = name.capitalize()
        this.nameDec = name.decapitalize()
    }
}


class MetaEntity : MetaMatter
{
    val primaryInterface: KClass<out AbElement>
    val isFinal: Boolean

    var children = ArrayList<MetaChild>()
    var properties = ArrayList<MetaProperty>()

    constructor(primaryInterface: KClass<out AbElement>, isFinal: Boolean)
            : super(primaryInterface.simpleName!!)
    {
        this.primaryInterface = primaryInterface
        this.isFinal = isFinal
    }
}


class MetaChild : MetaMatter
{
    val koProperty: KProperty<Any?>

    constructor(property: KProperty<Any?>)
            : super(property.name)
    {
        this.koProperty = property
    }
}


class MetaProperty : MetaMatter
{
    val koProperty: KProperty<Any?>

    constructor(property: KProperty<Any?>)
            : super(property.name)
    {
        this.koProperty = property
    }
}