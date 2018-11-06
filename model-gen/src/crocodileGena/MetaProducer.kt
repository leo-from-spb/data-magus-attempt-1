package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.util.choose
import org.jetbrains.datamagus.util.pad
import org.jetbrains.datamagus.util.toStrings
import org.jetbrains.datamagus.util.toText
import java.nio.file.Files
import java.nio.file.Paths

class MetaProducer
(
    val model: MetaModel
)
{
    private val pathToApi = Paths.get("model-api", "gen", "content")

    fun init() {
        assert(Files.exists(pathToApi)) { "No folder $pathToApi" }
    }


    fun produceCodeFiles()
    {
        produceCodeFilesForFix()
        produceCodeFilesForVar()
    }

    private fun produceCodeFilesForFix()
    {
        for (a in model.areas)
        {
            if (a.code == "Ab") continue
            val filePath = pathToApi.resolve("Fix${a.name}.kt")
            val text = a.produceFixText().replace('¬', '\t')
            Files.write(filePath, listOf(text))
        }
    }

    private fun MetaArea.produceFixText() =
            """
            |// DataMagus Model Fixed Area $name
            |// =======================================
            |
            |package org.jetbrains.datamagus.model.content
            |
            |import org.jetbrains.datamagus.model.ancillary.*
            |import org.jetbrains.datamagus.model.content.*
            |
            |${entities.filter(MetaEntity::isFinal).toText(delimiter = "\n\n"){produceFixText()}}
            |
            """.trimMargin()

    private fun MetaEntity.produceFixText() =
            """
            |
            |${isFinal.choose("final", "abstract")} class Fix$klassName
            |(
            |¬${produceFixContent().joinToString(separator = ",\n¬")}
            |)
            |: FixElement(id), $klassName
            |{
            | 	override val families: List<FixFamily<FixElement>> get() = ${allFamilies.toText(", ", "listOf(", ")", empty = "emptyList()") { familyName }}
            |}
            |
            """.trimMargin()

    private fun MetaEntity.produceFixContent() =
            listOf("id: Int") +
            allFamilies.toStrings { produceFixText() } +
            allProperties.toStrings { produceFixText() }

    private fun MetaFamily.produceFixText() =
            """
            |override val ${familyName pad 15}: ${"FixFamily<Fix$innerClassName>" pad 26} = EmptyFamily
            """.trimMargin()

    private fun MetaProperty.produceFixText() =
            """
            |override val ${propName pad 15}: ${propTypeFull pad 26} = $propDefault
            """.trimMargin()


    private fun produceCodeFilesForVar()
    {

    }

}