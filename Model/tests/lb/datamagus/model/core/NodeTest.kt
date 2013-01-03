package lb.datamagus.model.core;

import lb.testutils.*
import org.testng.annotations.*

class NodeTest : BaseModelTestCase()
{

    class TestNode (nip: NIP) : Node (nip)
    {
        val refA = makeRef<TestNode>()
        val refB = makeRef<TestNode>()
    }



    [Test]
    fun test_init_basic()
    {
        model.modify("Test") {
            val node = TestNode(newNIP(it))

            node.model _same_as_ model

            node.id _greater_or_equal_ 1
            node.parent._null_()
            node.dropt._false_()
        }
    }


    //// BASIC TESTS \\\\


    [Test]
    fun test_init_register()
    {
        model.modify("Test") {
            it.countNodes _equals_ 0
            val node = TestNode(newNIP(it))
            it.countNodes _equals_ 1
            val x = it.node<TestNode>(node.id)
            x _same_as_ node
        }
    }


    [Test]
    fun test_drop_dropt()
    {
        model.modify("Test") {
            val node = TestNode(newNIP(it))
            node.dropt ._false_()
            node.drop()
            node.dropt ._true_()
        }
    }


    [Test]
    fun test_drop_unregister()
    {
        model.modify("Test") {
            val node = TestNode(newNIP(it))
            val id = node.id

            node.drop()

            (it hasNode id)  ._false_()
        }
    }


    //// REF TESTS \\\\

    [Test]
    fun test_Ref_1()
    {
        model.modify("Test") {
            val x = TestNode(newNIP(it))
            val y = TestNode(newNIP(it))
            val z = TestNode(newNIP(it))

            x.refA.node ._null_()
            x.refA.node = y
            x.refA.node _same_as_ y
            x.refA.node = z
            x.refA.node _same_as_ z
            x.refA.node = null
            x.refA.node ._null_()
        }
    }


    [Test]
    fun test_Ref_references()
    {
        model.modify("Test") {
            val x = TestNode(newNIP(it))
            val y = TestNode(newNIP(it))
            val z = TestNode(newNIP(it))

            x _not_in_ y.references
            x _not_in_ z.references

            x.refA.node = y

            x _in_ y.references
            x _not_in_ z.references

            x.refA.node = z

            x _not_in_ y.references
            x _in_ z.references

            x.refA.node = null

            x _not_in_ y.references
            x _not_in_ z.references
        }
    }


    [Test]
    fun test_Ref_references_2times()
    {
        model.modify("Test") {
            val x = TestNode(newNIP(it))
            val y = TestNode(newNIP(it))

            x _not_in_ y.references

            x.refA.node = y

            x _in_ y.references

            x.refB.node = y // another ref to the same node

            x _in_ y.references

            x.refA.node = null  // B still references

            x _in_ y.references

            x.refB.node = null  // B still references

            x _not_in_ y.references
        }
    }




}

