package lb.datamagus.model.core;

import org.testng.annotations.*
import kotlin.dom.nodesToXmlString
import sun.net.www.content.text.PlainTextInputStream
import org.testng.Assert


public class BoneTest
{

    class TestBone (nip: NIP) : Bone (nip) {}

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
    }


    [Test]
    fun test_init_name()
    {
        val bone = TestBone(NIP(model = model))

        Assert.assertNotNull(bone.name)
        Assert.assertTrue(bone.name.length > 0)
    }






}

