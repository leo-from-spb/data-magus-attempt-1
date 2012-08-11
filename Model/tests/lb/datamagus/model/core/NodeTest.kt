package lb.datamagus.model.core;

import org.testng.annotations.*
import org.testng.Assert
import lb.datamagus.model.core.Node.Ref
import lb.datamagus.model.core.Node.RefPoint


class NodeTest
{

    class TestNode (nip: NIP) : Node (nip)
    {
        val refA = makeRef<TestNode>()
        val refB = makeRef<TestNode>()
    }


    var model = Model()




    [BeforeMethod]
    fun beforeMethod()
    {
        model = Model()  // instead of cleanup
    }


    [Test]
    fun test_init_basic()
    {
        val node = TestNode(NIP(model))

        Assert.assertEquals(node.model, model)
        Assert.assertTrue(node.id >= 1)
        Assert.assertNull(node.parent)
        Assert.assertFalse(node.dropt)
    }


    //// BASIC TESTS \\\\


    [Test]
    fun test_init_register()
    {
        Assert.assertEquals(model.countNodes, 0)

        val node = TestNode(NIP(model))

        Assert.assertEquals(model.countNodes, 1)

        val x = model.node<TestNode>(node.id)

        Assert.assertSame(x, node)
    }


    [Test]
    fun test_drop_dropt()
    {
        val node = TestNode(NIP(model))

        Assert.assertFalse(node.dropt)

        node.drop()

        Assert.assertTrue(node.dropt)
    }


    [Test]
    fun test_drop_unregister()
    {
        val node = TestNode(NIP(model))
        val id = node.id

        node.drop()

        Assert.assertFalse(model hasNode id)
    }


    //// REF TESTS \\\\

    [Test]
    fun test_Ref_1()
    {
        val x = TestNode(NIP(model))
        val y = TestNode(NIP(model))
        val z = TestNode(NIP(model))

        Assert.assertNull(x.refA.node)

        x.refA.node = y

        Assert.assertEquals(x.refA.node, y)

        x.refA.node = z

        Assert.assertEquals(x.refA.node, z)

        x.refA.node = null;

        Assert.assertNull(x.refA.node)
    }


    [Test]
    fun test_Ref_references()
    {
        val x = TestNode(NIP(model))
        val y = TestNode(NIP(model))
        val z = TestNode(NIP(model))

        Assert.assertFalse(x in y.references)
        Assert.assertFalse(x in z.references)

        x.refA.node = y

        Assert.assertTrue(x in y.references)
        Assert.assertFalse(x in z.references)

        x.refA.node = z

        Assert.assertFalse(x in y.references)
        Assert.assertTrue(x in z.references)

        x.refA.node = null;

        Assert.assertFalse(x in y.references)
        Assert.assertFalse(x in z.references)
    }


    [Test]
    fun test_Ref_references_2times()
    {
        val x = TestNode(NIP(model))
        val y = TestNode(NIP(model))

        Assert.assertFalse(y in x.references)

        x.refA.node = y

        Assert.assertTrue(x in y.references)

        x.refB.node = y // another ref to the same node

        Assert.assertTrue(x in y.references)

        x.refA.node = null;  // B still references

        Assert.assertTrue(x in y.references)

        x.refB.node = null;  // B still references

        Assert.assertFalse(x in y.references)
    }





}

