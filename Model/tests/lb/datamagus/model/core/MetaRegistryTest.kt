package lb.datamagus.model.core

import lb.testutils.*
import org.testng.annotations.*

public class MetaRegistryTest
{

    val registry = MetaInitializer.registry;

    [Test]
    fun testNodeIsRegistered()
    {
        registry descriptionFor "Node" // expect no exception
    }


    [Test(dependsOnMethods=array("testNodeIsRegistered"))]
    fun testBoneIsNode()
    {
        val nodeDesc = registry descriptionFor "Node"
        val boneDesc = registry descriptionFor "Bone"
        boneDesc.base _same_as_ nodeDesc
    }


}
