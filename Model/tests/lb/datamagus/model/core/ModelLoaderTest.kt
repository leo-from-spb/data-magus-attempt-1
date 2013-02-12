package lb.datamagus.model.core

import com.google.common.collect.ImmutableList
import lb.testutils.*
import lb.utils.toMap
import org.testng.annotations.*

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

    [Test]
    fun testExportNode_Ref()
    {
        model.modify("Test") { model ->

            val bone1 = DumbTestBone(newNIP(model))
            val bone2 = DumbTestBone(newNIP(model))
            bone1.refProp.node = bone2

            val delta = loader.exportNode(bone1)

            bone1.id _equals_ 1

            val refStr = delta.props.find{p -> p.propertyName == "RefProp"}!!.neo
            refStr  _equals_ "#2"
        }
    }

    [Test]
    fun testExportNode_Refs()
    {
        model.modify("Test") { model ->

            val bone1 = DumbTestBone(newNIP(model))
            val bone2 = DumbTestBone(newNIP(model))
            val bone3 = DumbTestBone(newNIP(model))
            val bone4 = DumbTestBone(newNIP(model))
            bone1.refsProp.nodes = ImmutableList.of(bone2,bone3,bone4)!!;

            bone1.id _equals_ 1

            val delta = loader.exportNode(bone1)

            val refsStr = delta.props.find{p -> p.propertyName == "RefsProp"}!!.neo
            refsStr _equals_ "##2,3,4"
        }
    }


    [Test]
    fun testExport_complex()
    {
        model.modify("Test Model") { model ->

            val root = model.createProjectRoot()
            val conceptual = root.conceptuals.create { name = "Polikom Pro" }
            val domain1 = conceptual.domains.create { name = "abstract" }
            val domain2 = conceptual.domains.create { name = "name word" }
            val entity1 = conceptual.entities.create { name = "Org" }
            val attr11 = entity1.attributes.create { name = "Id"; domain.node = domain1 }
            val attr12 = entity1.attributes.create { name = "Name"; domain.node = domain2 }
            val entity2 = conceptual.entities.create { name = "Person" }
            val attr21 = entity2.attributes.create { name = "Id"; domain.node = domain1 }
            val attr22 = entity2.attributes.create { name = "Name"; domain.node = domain2 }

        }

        val modification =
                model.read { model -> loader.exportWholeModelAsModification(model, "Polikom Pro") }

        val deltaOrg = modification.deltas.find{d -> d.nodeDisplayName == "Org"}
        val deltaPerson = modification.deltas.find{d -> d.nodeDisplayName == "Person"}

        deltaOrg    ._not_null_()
        deltaPerson ._not_null_()

        val deltaAbstract = modification.deltas.find{d -> d.nodeDisplayName == "abstract"}!!
        val deltaId = modification.deltas.find {d -> d.nodeDisplayName == "Id"}!!

        deltaId.props.find {p -> p.propertyName == "Domain"}!!.neo _equals_ "#${deltaAbstract.id}"
    }

}