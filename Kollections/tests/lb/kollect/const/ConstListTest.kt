package lb.kollect.const;

import org.testng.annotations.*
import org.testng.Assert



class ConstListTest
{


    [Test]
    fun test_of_0()
    {
        val list = listOf<Int>();

        Assert.assertEquals(list.size, 0)
        Assert.assertEquals(list.isEmpty, true)
        Assert.assertEquals(list.isNotEmpty, false)
    }

    [Test]
    fun test_of_1()
    {
        val list = listOf(1111);

        Assert.assertEquals(list.size, 1)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)

        Assert.assertEquals(list.first, 1111)
        Assert.assertEquals(list.last, 1111)
    }

    [Test]
    fun test_of_2()
    {
        val list = listOf(1111, 2222);

        Assert.assertEquals(list.size, 2)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)

        Assert.assertEquals(list.first, 1111)
        Assert.assertEquals(list.last, 2222)
    }

    [Test]
    fun test_of_2s()
    {
        val list = listOf("Aaaa", "Bbbb");

        Assert.assertEquals(list.size, 2)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)

        Assert.assertEquals(list.first, "A" + "aaa")
        Assert.assertEquals(list.last, "B" + "bbb")
    }

    [Test]
    fun test_of_3()
    {
        val list = listOf(1111, 2222, 3333);

        Assert.assertEquals(list.size, 3)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)

        Assert.assertEquals(list.first, 1111)
        Assert.assertEquals(list.last, 3333)
    }


    [Test]
    fun test_iterator_3()
    {
        val list = listOf(1111, 2222, 3333);
        val it = list.iterator()

        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 1111)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 2222)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 3333)
        Assert.assertFalse(it.hasNext)
    }




}

