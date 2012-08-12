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


    [Test]
    fun test_plus_0()
    {
        val list0 = listOf<String>()
        val list1 = list0 + "balalaika"

        Assert.assertEquals(list1.size, 1)
        Assert.assertEquals(list1[0], "balalaika")
    }

    [Test]
    fun test_plus_1()
    {
        val list1 = listOf<String>("trumpet")
        val list2 = list1 + "balalaika"

        Assert.assertEquals(list2.size, 2)
        Assert.assertEquals(list2[0], "trumpet")
        Assert.assertEquals(list2[1], "balalaika")
    }

    [Test]
    fun test_plus_2()
    {
        val list2 = listOf<String>("trumpet", "duduk")
        val list3 = list2 + "balalaika"

        Assert.assertEquals(list3.size, 3)
        Assert.assertEquals(list3[0], "trumpet")
        Assert.assertEquals(list3[1], "duduk")
        Assert.assertEquals(list3[2], "balalaika")
    }


    [Test]
    fun test_equals_0()
    {
        val listA = listOf<String>()
        val listB = listOf<String>()
        Assert.assertTrue(listA equals listB)
    }

    [Test]
    fun test_equals_1()
    {
        val listA = listOf<String>("10")
        val listB = listOf<String>("1"+"0")
        Assert.assertTrue(listA equals listB)
    }

    [Test]
    fun test_equals_3()
    {
        val listA = listOf<String>("11","12","13")
        val listB = listOf<String>("11","12","13")
        Assert.assertTrue(listA equals listB)
    }



}

