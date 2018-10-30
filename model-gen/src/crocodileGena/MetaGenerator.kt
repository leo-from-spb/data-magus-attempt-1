package org.jetbrains.datamagus.model.crocodileGena

import java.nio.file.Paths

object MetaGenerator
{

    @JvmStatic
    fun main(args: Array<String>) {
        say('\n' + " ".repeat(80) + '\n')
        say("Gena the Crocodile")

        val model = MetaModel()
        val loader = MetaLoader(model)
        val processor = MetaProcessor(model)

        loader.loadMetaModel()
        processor.process()
        processor.report(Paths.get("model-api/meta-model.txt"))

        say("\nOk.\n")
    }

}