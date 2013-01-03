package lb.datamagus.model.core;

import lb.testutils.*
import org.testng.annotations.*

class ProjectRootTest : BaseModelTestCase()
{

    [Test]
    fun test_createProjectRoot()
    {
        model.modify("Test") {
            val r1 = it.createProjectRoot()
            val r2 = it.getProjectRoot()

            r2 _same_as_ r1
            r1 _in_ it.viewAllNodes()
        }
    }


    [Test]
    fun test_newConceptual()
    {
        model.modify("Test") {
            val pr = ProjectRoot(newNIP(it))
            val cm = pr.conceptuals create { name = "My Conceptual Model" }

            cm _in_ pr.conceptuals
            cm.name _equals_ "My Conceptual Model"
        }
    }


}

