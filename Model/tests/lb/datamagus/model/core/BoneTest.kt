package lb.datamagus.model.core;

import lb.testutils.*
import org.testng.annotations.*

class BoneTest : BaseModelTestCase()
{

    class TestBone (nip: NIP) : Bone (nip) {}



    [Test]
    fun test_init_name()
    {
        model.modify("Test") {
            val bone = TestBone(newNIP(model = it))
            bone.name._not_null_()
            bone.name.length _greater_ 0
        }
    }






}

