package lb.datamagus.model.concept;

import lb.datamagus.model.core.Model
import lb.testutils.*
import org.testng.annotations.*

class AttributeTest
{

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        val m = Model()  // instead of cleanup
        m.modify("Init model") {
            val projectRoot = m.createProjectRoot()
            val conceptual = projectRoot.conceptuals create {}
            conceptual.entities create {name = "Entity 1"}
            conceptual.entities create {name = "Entity 2"}
        }
        this.model = m
    }



    [Test]
    fun test_domain_ref()
    {
        model.modify("Test") {
            val conceptual = model.getProjectRoot().conceptuals.first!!
            val domA = conceptual.domains create {name = "Domain A"}
            val entity1 = conceptual.entities.first!!
            val a1 = entity1.attributes create {name = "Attr 1"}

            a1.domain.node = domA

            a1.domain.node _same_as_ domA

            a1 _in_ domA.references
        }
    }


}

