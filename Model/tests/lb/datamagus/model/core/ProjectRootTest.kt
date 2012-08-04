package lb.datamagus.model.core;


import org.testng.annotations.*
import kotlin.dom.nodesToXmlString
import sun.net.www.content.text.PlainTextInputStream
import org.testng.Assert


public class ProjectRootTest
{

    class TestBone (bip: BIP) : Bone (bip) {}

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
    }


    [Test]
    fun test_newConceptual()
    {
        // ASK
        // java.lang.VerifyError: (class: lb/datamagus/model/core/ProjectRoot, method: newConceptual signature: (Ljava/lang/String;)Llb/datamagus/model/concept/Conceptual;) Incompatible argument to function
        // at lb.datamagus.model.core.ProjectRootTest.test_newConceptual(ProjectRootTest.kt:32)

        val pr = ProjectRoot(BIP(model))
        val cm = pr.newConceptual("My Conceptual Model")

        Assert.assertTrue(cm in pr.conceptuals)
        Assert.assertEquals(cm.name, "My Conceptual Model")
    }



}

