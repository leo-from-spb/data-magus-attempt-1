package lb.datamagus.model.concept;

import org.testng.annotations.*
import org.testng.Assert
import lb.datamagus.model.core.*


class EntityTest
{

    var model = Model()
    var conceptual1: Conceptual? = null


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
        model.createProjectRoot()
        conceptual1 = model.getProjectRoot().conceptuals create { name = "My Test Conceptual" }
    }


    [Test]
    fun test1()
    {
        val e1 = conceptual1!!.entities create {}
        val e2 = conceptual1!!.entities create {}
        Assert.assertNotEquals(e1.name, e2.name)
    }

}

