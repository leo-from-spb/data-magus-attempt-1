package lb.datamagus.model.core;


import org.testng.annotations.*
import org.testng.Assert


class ProjectRootTest
{

    class TestBone (bip: BIP) : Bone (bip) {}

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
        val pr = ProjectRoot(BIP(model))
        val cm = pr.newConceptual("My Conceptual Model")

        Assert.assertTrue(cm in pr.conceptuals)
        Assert.assertEquals(cm.name, "My Conceptual Model")
    }


}

