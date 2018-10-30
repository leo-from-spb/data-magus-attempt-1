package org.jetbrains.datamagus.model

import org.jetbrains.datamagus.model.content.*
import kotlin.reflect.KClass

object ModelMetaInfo
{

    val FinalEntities: List<KClass<out AbElement>> = listOf(
            ProProject::class,
            ConModel::class,
            ConSubArea::class,
            ConEntity::class,
            ConAttribute::class
    )

}