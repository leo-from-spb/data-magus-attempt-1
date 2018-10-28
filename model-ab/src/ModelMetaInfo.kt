package com.jetbrains.datamagus.model

import com.jetbrains.datamagus.model.content.*
import kotlin.reflect.KClass

object ModelMetaInfo
{

    val FinalElements: List<KClass<out AbElement>> = listOf(
            ProProject::class,
            ConModel::class,
            ConSubArea::class,
            ConEntity::class,
            ConAttribute::class
    )

}