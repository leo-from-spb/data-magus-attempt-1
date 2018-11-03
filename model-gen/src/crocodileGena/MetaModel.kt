package org.jetbrains.datamagus.model.crocodileGena

class MetaModel
{
    val areas = listOf(
            MetaArea("Ab", "Abstract"),
            MetaArea("Pro", "Project"),
            MetaArea("Con", "Concept"),
            MetaArea("Db", "Database"),
            MetaArea("Dia", "Diagram")
    )

    val areasByCodes = mutableMapOf<String, MetaArea>()

    val entities = mutableListOf<MetaEntity>()

    val entitiesByClass = mutableMapOf<String, MetaEntity>()

}


