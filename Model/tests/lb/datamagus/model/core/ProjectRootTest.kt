package lb.datamagus.model.core;


import org.testng.annotations.*
import org.testng.Assert


class ProjectRootTest
{

    class TestBone (nip: NIP) : Bone (nip) {}

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
    }


    [Test]
    fun test_createProjectRoot()
    {
        val r1 = model.createProjectRoot()
        val r2 = model.getProjectRoot()

        Assert.assertSame(r2, r1)
        Assert.assertTrue(r1 in model.viewAllNodes())
    }


    [Test]
    fun test_newConceptual()
    {
        val pr = ProjectRoot(NIP(model))
        val cm = pr.conceptuals create { name = "My Conceptual Model" }

        Assert.assertTrue(cm in pr.conceptuals)
        Assert.assertEquals(cm.name, "My Conceptual Model")
    }


}

