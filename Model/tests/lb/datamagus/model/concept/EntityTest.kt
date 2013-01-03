package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.testutils.*
import org.testng.annotations.*

class EntityTest : BaseModelTestCase()
{

    [BeforeMethod]
    override fun beforeMethod()
    {

        model.modify("Init project root") {
            val projectRoot = it.createProjectRoot()
            projectRoot.conceptuals create { name = "My Test Conceptual" }
        };
    }



    [Test]
    fun test1()
    {
        model.modify("Test") {
            val concept = it.getProjectRoot().conceptuals.first!!;
            val e1 = concept.entities create {}
            val e2 = concept.entities create {}

            e2.name _not_equals_ e1.name
            concept.entities _contains_ e1
            concept.entities _contains_ e2
        }
    }

}

