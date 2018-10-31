package org.jetbrains.datamagus.model.crocodileGena

import java.util.*

class MetaModel
{
    val areas = listOf<MetaArea>(
            MetaArea("Ab", "Abstract"),
            MetaArea("Pro", "Project"),
            MetaArea("Con", "Concept"),
            MetaArea("Db", "Database"),
            MetaArea("Dia", "Diagram")
    )

    val areaCodes: MutableMap<String, MetaArea> = TreeMap()

    val entities = ArrayList<MetaEntity>()




}


