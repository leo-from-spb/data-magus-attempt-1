package lb.datamagus.model.core;

import org.testng.annotations.*
import kotlin.dom.nodesToXmlString
import sun.net.www.content.text.PlainTextInputStream
import org.testng.Assert


public class NodeTest
{

    class TestNode (nip: NIP) : Node (nip) {}

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


    [Test]
    fun test_init_register()
    {
        Assert.assertEquals(model.countNodes, 0)

        val node = TestNode(NIP(model))

        Assert.assertEquals(model.countNodes, 1)

        // ASK
        // val x = model node<TestNode> node.id
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
    // ASK [Test(dependsOnMethods="test_init_register")]
    fun test_drop_unregister()
    {
        val node = TestNode(NIP(model))
        val id = node.id

        node.drop()

        Assert.assertFalse(model hasNode id)
    }




}

