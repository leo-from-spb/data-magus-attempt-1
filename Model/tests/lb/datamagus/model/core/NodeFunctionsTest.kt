package lb.datamagus.model.core

import lb.testutils.*
import org.testng.annotations.*
import java.util.ArrayList


class NodeFunctionsTest : BaseModelTestCase()
{


    [Test]
    fun testTraverseAll()
    {
        // prepare the model
        model.modify ("Make model")
            {
                val root = it.createProjectRoot();
                root.name = "My Project"
                val cm1 = root.conceptuals.create { name = "My Concept 1" }
                val cm2 = root.conceptuals.create { name = "My Concept 2" }
                cm1.subAreas.create { name = "My SubArea 1.1" }
                cm1.subAreas.create { name = "My SubArea 1.2" }
                cm2.subAreas.create { name = "My SubArea 2.1" }
                cm2.subAreas.create { name = "My SubArea 2.2" }
            }

        // test
        val names = ArrayList<String>()
        model.read {
            it.getProjectRoot().traverseAll { node -> names.add(node.displayName) }
        }

        // verify
        names._list_("My Project",
                     "My Concept 1",
                     "My SubArea 1.1",
                     "My SubArea 1.2",
                     "My Concept 2",
                     "My SubArea 2.1",
                     "My SubArea 2.2")
    }

}