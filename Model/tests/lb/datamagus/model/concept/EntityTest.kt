package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.testutils.*
import org.testng.annotations.*

class EntityTest
{

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
        model.createProjectRoot()
        model.getProjectRoot().conceptuals create { name = "My Test Conceptual" }
    }


    [Test]
    fun test1()
    {
        val concept = model.getProjectRoot().conceptuals.first!!;
        val e1 = concept.entities create {}
        val e2 = concept.entities create {}

        e2.name _not_equals_ e1.name
        concept.entities _contains_ e1
        concept.entities _contains_ e2
    }

}

