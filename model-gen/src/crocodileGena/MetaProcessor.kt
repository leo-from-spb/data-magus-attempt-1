package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.util.choose
import org.jetbrains.datamagus.util.splitByFirstCap
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
        for (area in model.areas) processArea(area)
        for (entity in model.entities) processEntity(entity)
    }

    private fun processArea(a: MetaArea)
    {
        model.areaCodes[a.code] = a
    }

    private fun processEntity(e: MetaEntity)
    {
        val primaryInterfaceName = e.primaryInterface.simpleName!!
        val (areaCode, entityName) = primaryInterfaceName.splitByFirstCap()
        e.area = model.areaCodes[areaCode] ?: error("No area with code $areaCode")
        e.name = entityName
        e.area.entities += e

        for (property in e.properties) processProperty(e, property)
    }

    private fun processProperty(e: MetaEntity, p: MetaProperty)
    {
        p.name = p.koProperty.name
        p.type = p.koProperty.returnType.toString()
        p.nullable = p.koProperty.returnType.isMarkedNullable
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
        |
        |AREAS
        |------------------------------------
        |Code  Name                       Ent
        |------------------------------------
        |${model.areas.toText { "${code.padEnd(4)}  ${name.padEnd(26)} ${entities.size.toStringPad(3)}" }}
        |------------------------------------
        |
        |
        |ENTITIES
        |--------------------------------------
        |F  Name                       Chi Prop
        |--------------------------------------
        |${model.entities.toText { "${isFinal.choose('@','+')}  ${name.padEnd(26)} ${children.size.toStringPad(3)}  ${properties.size.toStringPad(3)}" }}
        |--------------------------------------
        |
        |
        """.trimMargin()



}