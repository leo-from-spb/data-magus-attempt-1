package org.jetbrains.datamagus.model.crocodileGena

import org.jetbrains.datamagus.util.choose
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
        assert(Files.exists(pathToApi)) { "No floder $pathToApi" }
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
            |${entities.filter(MetaEntity::isFinal).toText(delimiter = "\n\n"){produceFixText()}}
            |
            """.trimMargin()

    private fun MetaEntity.produceFixText() =
            """
            |
            |${isFinal.choose("final", "abstract")} class Fix$primaryName
            |{
            |
            |¬${properties.toText(delimiter = "\n¬") {produceFixText()}}
            |
            |}
            |
            """.trimMargin()

    private fun MetaProperty.produceFixText() =
            """
            |override val $name: $type
            """.trimMargin()


    private fun produceCodeFilesForVar()
    {

    }

}