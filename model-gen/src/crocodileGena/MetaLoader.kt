package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.model.ModelMetaInfo
import org.jetbrains.datamagus.model.ancillary.Family
import org.jetbrains.datamagus.model.content.AbElement
import org.jetbrains.datamagus.util.choose
import org.jetbrains.datamagus.util.prefixed
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties


class MetaLoader
(
        private val model: MetaModel
)
{

    fun loadMetaModel()
    {
        say("Loading model classes: ")

        for (finalElementKlass in ModelMetaInfo.FinalEntities) loadEntity(finalElementKlass, true)

        say("\tloaded ${model.entities.size} classes.")
    }

    private fun loadEntity(primaryInterface: KClass<out AbElement>, isFinal: Boolean)
    {
        val found = model.entities.find { it.primaryInterface == primaryInterface }
        if (found != null)
        {
            if (found.isFinal == isFinal) return // already loaded
            else throw Exception("Loading collision in entity ${primaryInterface.simpleName}: ambiguous finality")
        }

        val superTypes =
                primaryInterface
                    .supertypes
                    .filter { it.arguments.isEmpty() }
                    .mapNotNull { it.classifier as? KClass<*> }
                    .filter { it.qualifiedName prefixed "org.jetbrains.datamagus.model.content." }
        for (superType in superTypes)
        {
            @Suppress("unchecked_cast")
            val superInterface = superType as KClass<out AbElement>
            loadEntity(superInterface, false)
        }

        say("\t${isFinal.choose('@', '+')} ${primaryInterface.simpleName}")

        val entity = MetaEntity(primaryInterface, isFinal)
        model.entities += entity

        loadChildrenAndProperties(entity)
    }


    private fun loadChildrenAndProperties(entity: MetaEntity)
    {
        val koProperties =
                entity.primaryInterface
                    .declaredMemberProperties
                    .filter { it.isAbstract && it.visibility == KVisibility.PUBLIC && !it.isFinal }
        for (p: KProperty<Any?> in koProperties)
        {
            if (p.returnType.classifier == Family::class) {
                val child = MetaFamily(p)
                entity.children.add(child)
            }
            else {
                val property = MetaProperty(p)
                entity.properties.add(property)
            }
        }
    }

}