package lb.datamagus.model.core;

import lb.testutils.*
import org.testng.annotations.*
import lb.datamagus.model.core.Node.Family

class BoneTest : BaseModelTestCase()
{

    class TestBone (nip: NIP) : Bone (nip)
    {
        val bones: Family<TestBone> = makeFamily(javaClass<TestBone>())
        override fun children(): List<Node> { return bones }
    }



    [Test]
    fun test_init_name()
    {
        model.modify("Test") {
            val bone = TestBone(newNIP(model = it))
            bone.name._not_null_()
            bone.name.length _greater_ 0
        }
    }

    [Test]
    fun test_name_1()
    {
        model.modify("Test") {
            val bone = TestBone(newNIP(model = it))
            bone.name = "Белая Косточка"
            bone.name _equals_ "Белая Косточка"
            bone.displayName _equals_ "Белая Косточка"
        }
    }

    [Test]
    fun test_name_2()
    {
        model.modify("Test") {
            val bone1 = TestBone(newNIP(model = it))
            val bone2 = bone1.bones.create { name = "Чёрная Косточка" }
            bone2.name _equals_ "Чёрная Косточка"
            bone2.displayName _equals_ "Чёрная Косточка"
        }
    }




}

