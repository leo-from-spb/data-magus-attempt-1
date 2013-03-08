package lb.datamagus.model.loader

import lb.datamagus.model.core.RealDataMagusModel
import lb.datamagus.model.core.WorkModel
import lb.testutils.*
import org.testng.annotations.*

class FileLoaderTest
{

    fun prepareBasicModel(): WorkModel
    {
        val wm = RealDataMagusModel()
        wm.modify("Init basic test model") { m ->
            val root = m.createProjectRoot()
            root.name = "Basic Test Model"

            val concept = root.conceptuals.create { name = "Basic Conceptual Model" }

            val domainAbstract = concept.domains.create { name = "abstract" }
            val domainWord = concept.domains.create { name = "word" }

            val entityOrg = concept.entities.create { name = "Org" }
            entityOrg.attributes.create { name = "Id"; domain.node = domainAbstract }
            entityOrg.attributes.create { name = "Name"; domain.node = domainWord }
        }

        return wm
    }

    [Test]
    fun dummy()
    {
        //
    }


    [Test]
    fun testSaveModelToFile_basic()
    {
        val dir = lb.datamagus.model.TEMPO.getDirectory("basic")
        val model = prepareBasicModel()
        val loader = FileLoader()

        val file = dir.resolve("basic.dm")!!.toFile()!!
        loader.saveModelToFile(model, file)

        file.exists() ._true_()
    }


}

