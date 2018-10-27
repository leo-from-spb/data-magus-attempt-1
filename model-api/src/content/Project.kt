package org.jetbrains.datamagus.model.content

interface ProProject : AbElement {

    /**
     * Version of the model content.
     */
    val version: Int

    /**
     * Name of the project.
     */
    val name: String

}