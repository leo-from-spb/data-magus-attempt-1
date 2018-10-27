package org.jetbrains.datamagus.model.base

import org.jetbrains.datamagus.model.content.AbRoot

class BaseRoot : BaseElement, AbRoot
{

    override val version: Int

    constructor(model: BaseModel) : super(model) {
        version = model.rootVersionCounter.incrementAndGet()
    }
    
}