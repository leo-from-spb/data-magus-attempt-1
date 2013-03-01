package lb.datamagus.model.core

import lb.testutils.*
import org.testng.annotations.*

public class MetaRegistryTest
{

    [Test]
    fun testNodeIsRegistered()
    {
        val d = registry["Node"] // expect no exception
        d.name _equals_ "Node"
    }

    [Test]
    fun testBoneIsRegistered()
    {
        val d = registry["Bone"] // expect no exception
        d.name _equals_ "Bone"
    }


    [Test(dependsOnMethods=array("testNodeIsRegistered", "testBoneIsRegistered"))]
    fun testBoneIsNode()
    {
        val nodeDesc = registry get "Node"
        val boneDesc = registry get "Bone"
        boneDesc.base _same_as_ nodeDesc
    }


    [Test(dependsOnMethods=array("testBoneIsRegistered"))]
    fun testBoneHasName()
    {
        val boneDesc = registry get "Bone"
        boneDesc["Name"] ._not_null_()
    }


    [Test(dependsOnMethods=array("testBoneHasName"))]
    fun testNameIsString()
    {
        val boneDesc = registry get "Bone"
        val nameDesc = boneDesc["Name"]
        nameDesc.ptype _equals_ PropertyType.Str
    }



    val dumbTestBoneClass = javaClass<DumbTestBone>()


    [Test(dependsOnMethods=array("testBoneIsRegistered"))]
    fun registerDumbTestBone()
    {
        registry.registerNodeDescriptor(dumbTestBoneClass)
        registry["DumbTestBone"] // assert that it was registered
    }


    [Test(dependsOnMethods=array("registerDumbTestBone"))]
    fun testPropertiesTypes()
    {
        val dumb = registry["DumbTestBone"]
        dumb["BoolProp"].ptype _equals_ PropertyType.Bool
        dumb["IntProp"].ptype _equals_ PropertyType.Int
        dumb["StrProp"].ptype _equals_ PropertyType.Str
        dumb["RefProp"].ptype _equals_ PropertyType.Ref
        dumb["RefsProp"].ptype _equals_ PropertyType.Refs
    }

}
