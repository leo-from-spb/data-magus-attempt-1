package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.util.*
import java.nio.file.Files
import java.nio.file.Path

class MetaProcessor
(
        private val model: MetaModel
)
{

    fun process()
    {
        for (a in model.areas) processArea(a)
        for (e in model.entities) processEntity1(e)
        for (f in model.entities.flatMap(MetaEntity::families)) processFamily(f)
        for (p in model.entities.flatMap(MetaEntity::properties)) processProperty(p)

        for (entity in model.entities) processEntity2(entity)
    }

    private fun processArea(a: MetaArea)
    {
        model.areasByCodes[a.code] = a
    }

    private fun processEntity1(e: MetaEntity)
    {
        val kotName = e.klass.simpleName!!
        val (areaCode, entityName) = kotName.splitByFirstCap()
        e.area = model.areasByCodes[areaCode] ?: error("No area with code $areaCode")
        e.name = entityName
        e.area.entities += e
        model.entitiesByClass[e.klass.simpleName!!] = e
    }

    private fun processEntity2(e: MetaEntity)
    {
        val superSet = LinkedHashSet<MetaEntity>()
        for (superKlass in e.superKlasses)
        {
            val superEntity = model.entitiesByClass[superKlass.simpleName]!!
            e.supers += superEntity
            superSet += superEntity.allSupers
            superSet += superEntity
        }
        e.allSupers.addAll(superSet)

        e.allFamilies += e.allSupers.flatMap { it.families } + e.families
        e.allProperties += e.allSupers.flatMap { it.properties } + e.properties
    }


    private fun processFamily(f: MetaFamily)
    {
        val innerE: MetaEntity = model.entitiesByClass[f.innerClass.simpleName]
                          ?: throw Exception("No entity for family ${f.familyProp.name} of class ${f.innerClass.simpleName}")
        f.innerEntity = innerE
    }

    private fun processProperty(p: MetaProperty)
    {
        p.propName = p.propProp.name
        p.nullable = p.propProp.returnType.isMarkedNullable
        val simpleType = p.propProp.returnType.simpleName
        p.propTypeCore = simpleType
        p.propTypeFull = if (p.nullable) simpleType ensureSuffix "?" else p.propTypeCore
        p.propDefault = if (p.nullable) "null" else defaults[simpleType] ?: "???"
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
        |-------------------------------------------
        |F  Area Name                       Chi Prop
        |-------------------------------------------
        |${model.entities.toText { "${isFinal.choose('@','+')}  ${area.code.padEnd(4)} ${name.padEnd(26)} ${families.size.toStringPad(3)}  ${properties.size.toStringPad(3)}" }}
        |-------------------------------------------
        |
        |
        """.trimMargin()



}