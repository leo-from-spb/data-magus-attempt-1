package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.model.content.AbElement
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * The base class for all meta-model nodes
 */
sealed class MetaMatter
{

}


typealias ClassOfElement = KClass<out AbElement>


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
    val klass: ClassOfElement
    val klassName: String
    val isFinal: Boolean
    val superKlasses: List<ClassOfElement>

    lateinit var area: MetaArea
    lateinit var name: String // the name is Capitalized

    val supers        = mutableListOf<MetaEntity>()
    val families      = mutableListOf<MetaFamily>()
    val properties    = mutableListOf<MetaProperty>()

    val allSupers     = mutableListOf<MetaEntity>()
    val allFamilies   = mutableListOf<MetaFamily>()
    val allProperties = mutableListOf<MetaProperty>()

    constructor(koClass: ClassOfElement, isFinal: Boolean, superKlasses: List<ClassOfElement>)
    {
        this.klass = koClass
        this.klassName = koClass.simpleName!!
        this.isFinal = isFinal
        this.superKlasses = superKlasses
    }

    override fun toString() = "entity $klassName"
}


class MetaFamily : MetaMatter
{
    val familyProp: KProperty<Any?>
    val familyName: String
    val innerClass: ClassOfElement
    val innerClassName: String
    lateinit var innerEntity: MetaEntity

    constructor(familyProp: KProperty<Any?>)
    {
        this.familyProp = familyProp
        this.familyName = familyProp.name
        @Suppress("unchecked_cast")
        innerClass = familyProp.returnType.arguments[0].type!!.classifier as ClassOfElement
        innerClassName = innerClass.simpleName!!
    }

    override fun toString() = "family $familyName of $innerClassName"
}


class MetaProperty : MetaMatter
{
    val propProp: KProperty<Any?>

    lateinit var propName:     String
    lateinit var propTypeCore: String
    lateinit var propTypeFull: String
    lateinit var propDefault:  String

    var origin: MetaProperty? = null
    var nullable = false

    constructor(koProp: KProperty<Any?>)
    {
        this.propProp = koProp
    }

    override fun toString() = "property $propName: $propTypeFull"
}