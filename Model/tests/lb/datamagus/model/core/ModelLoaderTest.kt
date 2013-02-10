package lb.datamagus.model.core

import lb.testutils.*
import org.testng.annotations.*
import java.util.ArrayList
import lb.utils.toMap


class ModelLoaderTest : BaseModelTestCase()
{

    val loader = ModelLoader()


    [Test]
    fun testExportNode_basic()
    {
        model.modify("Test") { model ->

            val bone = DumbTestBone(newNIP(model))
            bone.boolProp = true
            bone.intProp = 777
            bone.strProp = "bla-bla-bla"

            val delta = loader.exportNode(bone)

            delta.kind _equals_ Delta.Kind.Create
            delta.id   _equals_ bone.id

            val props = delta.props.toMap({p -> p.propertyName}, {p -> p.neo})
            props["BoolProp"]  _equals_ "+"
            props["IntProp"]   _equals_ "777"
            props["StrProp"]   _equals_ "bla-bla-bla"
        }
    }

}