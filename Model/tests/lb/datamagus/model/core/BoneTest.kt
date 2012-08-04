package lb.datamagus.model.core;

import org.testng.annotations.*
import kotlin.dom.nodesToXmlString
import sun.net.www.content.text.PlainTextInputStream
import org.testng.Assert


public class BoneTest
{

    class TestBone (bip: BIP) : Bone (bip) {}

    var model = Model()


    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
    }


    [Test]
    fun test_init_name()
    {
        // ASK mixed named and positioned arguments - Confluence says it is allowed
        // val bone = TestBone(BIP(model, name="White Bone"))
        val bone = TestBone(BIP(model = model, name="White Bone"))

        Assert.assertEquals(bone.name, "White Bone")
    }

    [Test]
    fun test_init_no_name()
    {
        val bone = TestBone(BIP(model = model))

        Assert.assertEquals(bone.name, "")
    }





}

