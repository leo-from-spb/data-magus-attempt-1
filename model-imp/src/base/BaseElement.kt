package org.jetbrains.datamagus.model.base

import org.jetbrains.datamagus.model.content.AbElement


open class BaseElement : AbElement {

    val model: BaseModel

    override val id: Int

    constructor(model: BaseModel) {
        this.model = model
        this.id = model.elementIdCounter.incrementAndGet()
    }


}

