package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.util.choose
import org.jetbrains.datamagus.util.toStringPad
import org.jetbrains.datamagus.util.toText
import java.nio.file.Files
import java.nio.file.Path

class MetaProcessor
(
        private val model: MetaModel
)
{

    fun process()
    {

    }

    fun report(reportPath: Path)
    {
        val text = generateReportText()

        Files.newBufferedWriter(reportPath, Charsets.UTF_8).use { stream ->
            stream.write(text)
        }
    }

    private fun generateReportText() =
        """
        |DataMagus Model Info
        |====================
        |
        |ENTITIES
        |--------------------------------------
        |F  Name                       Chi Prop
        |--------------------------------------
        |${model.entities.toText { "${isFinal.choose('@','+')}  ${nameCap.padEnd(26)} ${children.size.toStringPad(3)}  ${properties.size.toStringPad(3)}" }}
        |--------------------------------------
        |
        |
        """.trimMargin()



}