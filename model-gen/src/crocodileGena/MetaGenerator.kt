package org.jetbrains.datamagus.model.crocodileGena

object MetaGenerator
{

    @JvmStatic
    fun main(args: Array<String>) {
        say('\n' + " ".repeat(80) + '\n')
        say("Gena the Crocodile")

        loadMetaModel()

        say("\nOk.\n")
    }

}