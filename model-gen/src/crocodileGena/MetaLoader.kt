package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.model.ModelMetaInfo
import org.jetbrains.datamagus.model.content.AbElement
import org.jetbrains.datamagus.util.choose
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmErasure


fun loadMetaModel()
{
    say("Loading model classes: ")

    for (finalElementKlass in ModelMetaInfo.FinalElements) loadNode(finalElementKlass, true)

    say("\tloaded ${MetaModel.classes.size} classes.")
}

private fun loadNode(primaryInterface: KClass<out AbElement>, isFinal: Boolean)
{
    val found = MetaModel.classes.find { it.primaryInterface == primaryInterface }
    if (found != null)
    {
        if (found.isFinal == isFinal) return // already loaded
        else throw Exception("Loading collision in class ${primaryInterface.simpleName}: ambiguous finality")
    }

    val superTypes = primaryInterface.supertypes
    for (superType in superTypes)
    {
        val erasure = superType.jvmErasure
        if (erasure.qualifiedName?.startsWith("org.jetbrains.datamagus.model.content.") == true) {
            val superInterface = erasure as KClass<out AbElement>
            loadNode(superInterface, false)
        }
    }

    say("\t${isFinal.choose('@', '+')} ${primaryInterface.simpleName}")
    val metaNode = MetaNode(primaryInterface, isFinal)
    MetaModel.classes += metaNode
}

