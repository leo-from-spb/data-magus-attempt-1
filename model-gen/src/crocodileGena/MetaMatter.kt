package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.model.content.AbElement
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

sealed class MetaMatter
{

}


class MetaArea : MetaMatter
{
    val code: String
    val name: String

    val entities = mutableListOf<MetaEntity>()

    constructor(code: String, name: String)
    {
        this.code = code
        this.name = name
    }
}


class MetaEntity : MetaMatter
{
    val primaryInterface: KClass<out AbElement>
    val isFinal: Boolean

    lateinit var area: MetaArea
    lateinit var name: String // the name is Capitalized

    val nameDec      get() = name.decapitalize()
    val primaryName  get() = area.code + name


    val children   = mutableListOf<MetaFamily>()
    val properties = mutableListOf<MetaProperty>()

    constructor(primaryInterface: KClass<out AbElement>, isFinal: Boolean)
    {
        this.primaryInterface = primaryInterface
        this.isFinal = isFinal
    }
}


class MetaFamily : MetaMatter
{
    val koProperty: KProperty<Any?>

    constructor(property: KProperty<Any?>)
    {
        this.koProperty = property
    }
}


class MetaProperty : MetaMatter
{
    val koProperty: KProperty<Any?>

    lateinit var name: String
    lateinit var type: String

    var origin: MetaProperty? = null
    var nullable = false

    constructor(property: KProperty<Any?>)
    {
        this.koProperty = property
    }
}