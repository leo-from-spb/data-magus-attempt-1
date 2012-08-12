package lb.datamagus.model.concept;

import org.testng.annotations.*
import org.testng.Assert
import lb.datamagus.model.core.Node.Ref
import lb.datamagus.model.core.Node.RefPoint
import lb.datamagus.model.core.Model


class AttributeTest
{

    var model = Model()
    var projectRoot = model.createProjectRoot()
    var conceptual = projectRoot.conceptuals create {}
    var e1 = conceptual.entities create {name = "Entity 1"}
    var e2 = conceptual.entities create {name = "Entity 2"}


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
        projectRoot = model.createProjectRoot()
        conceptual = projectRoot.conceptuals create {}
        e1 = conceptual.entities create {name = "Entity 1"}
        e2 = conceptual.entities create {name = "Entity 2"}
    }



    [Test]
    fun test_domain_ref()
    {
        val domA = conceptual.domains create {name = "Domain A"}
        val a1 = e1.attributes create {name = "Attr 1"}

        a1.domain.node = domA

        Assert.assertSame(a1.domain.node, domA)

        Assert.assertTrue(a1 in domA.references)
    }


}

