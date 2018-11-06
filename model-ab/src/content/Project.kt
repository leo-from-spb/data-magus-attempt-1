package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.org.jetbrains.datamagus.model.ancillary.Prop

interface ProProject : AbNamedElement {

    /**
     * Version of the model content.
     */
    @Prop
    val version: Int

}