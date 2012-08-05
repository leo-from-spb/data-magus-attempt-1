package lb.kollect.const;

import org.testng.annotations.*
import kotlin.dom.nodesToXmlString
import sun.net.www.content.text.PlainTextInputStream
import org.testng.Assert



class ConstListTest
{


/*
    [Test]
    fun test_of_0()
    {
        val list = ConstList.of<Int>();

        Assert.assertEquals(list.size, 0)
        Assert.assertEquals(list.isEmpty, true)
        Assert.assertEquals(list.isNotEmpty, false)
    }
*/

    [Test]
    fun test_of_1()
    {
        val list = ConstList.of<Int>(1111);

        Assert.assertEquals(list.size, 1)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)

        Assert.assertEquals(list.first, 1111)
        Assert.assertEquals(list.last, 1111)
    }

    [Test]
    fun test_of_2()
    {
        val list = ConstList.of(1111, 2222);

        Assert.assertEquals(list.size, 2)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)

        Assert.assertEquals(list.first, 1111)
        Assert.assertEquals(list.last, 2222)
    }

}

