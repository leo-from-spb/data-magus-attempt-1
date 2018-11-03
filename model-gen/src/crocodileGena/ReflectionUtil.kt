package org.jetbrains.datamagus.model.crocodileGena

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmName


val KType.simpleName: String 
    get() =
        when (val x = classifier) {
            is KClass<*> -> x.simpleName ?: x.jvmName
            else -> x.toString()
        }

