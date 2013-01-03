package lb.datamagus.model.core;

import lb.testutils.*
import org.testng.annotations.*

class BoneTest
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
        model.modify("Test") {
            val bone = TestBone(NIP(model = model))
            bone.name._not_null_()
            bone.name.length _greater_ 0
        }
    }






}

