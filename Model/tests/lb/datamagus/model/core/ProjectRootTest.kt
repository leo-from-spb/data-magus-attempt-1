package lb.datamagus.model.core;

import lb.testutils.*
import org.testng.annotations.*

class ProjectRootTest
{

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
    }


    [Test]
    fun test_createProjectRoot()
    {
        model.modify("Test") {
            val r1 = model.createProjectRoot()
            val r2 = model.getProjectRoot()

            r2 _same_as_ r1
            r1 _in_ model.viewAllNodes()
        }
    }


    [Test]
    fun test_newConceptual()
    {
        model.modify("Test") {
            val pr = ProjectRoot(NIP(model))
            val cm = pr.conceptuals create { name = "My Conceptual Model" }

            cm _in_ pr.conceptuals
            cm.name _equals_ "My Conceptual Model"
        }
    }


}

