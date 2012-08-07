package lb.kollect.views;

import lb.kollect.const.*
import lb.kollect.intf.*
import lb.kollect.views.*
import org.testng.Assert
import org.testng.annotations.*

class JoinViewTest
{

    private val list123: List<Int> = listOf<Int>(111, 222, 333)
    private val list456: List<Int> = listOf<Int>(444, 555, 666)
    private val list789: List<Int> = listOf<Int>(777, 888, 999)

    private val list0: List<Int> = empty<Int>()



    //// SIMPLE CASE \\\\

    private val simpleJoin: List<Int> = join<Int>(list123, list456, list789)

    [Test]
    fun test_simple_size()
    {
        Assert.assertEquals(simpleJoin.size, 9)
    }

    [Test]
    fun test_simple_empty()
    {
        Assert.assertEquals(simpleJoin.isNotEmpty, true)
        Assert.assertEquals(simpleJoin.isEmpty, false)
    }

    [Test]
    fun test_simple_first_last()
    {
        Assert.assertEquals(simpleJoin.first, 111)
        Assert.assertEquals(simpleJoin.last, 999)
    }

    [Test]
    fun test_simple_contains()
    {
        Assert.assertTrue(simpleJoin.contains(111))
        Assert.assertTrue(simpleJoin.contains(222))
        Assert.assertTrue(simpleJoin.contains(333))
        Assert.assertTrue(simpleJoin.contains(444))
        Assert.assertTrue(simpleJoin.contains(555))
        Assert.assertTrue(simpleJoin.contains(666))
        Assert.assertTrue(simpleJoin.contains(777))
        Assert.assertTrue(simpleJoin.contains(888))
        Assert.assertTrue(simpleJoin.contains(999))
    }

    [Test]
    fun test_simple_contains_not()
    {
        Assert.assertFalse(simpleJoin.contains(1234))
        Assert.assertFalse(simpleJoin.contains("mura"))
    }


    [Test]
    fun test_simple_iterator()
    {
        val it = simpleJoin.iterator()

        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 111)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 222)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 333)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 444)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 555)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 666)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 777)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 888)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 999)

        Assert.assertFalse(it.hasNext)
    }


    //// EMPTY-SIMPLE-EMPTY CASE

    private val ese = join(list0, list123, list0)

    [Test]
    fun test_ese_size()
    {
        Assert.assertEquals(ese.size, 3)
    }

    [Test]
    fun test_ese_empty()
    {
        Assert.assertTrue(ese.isNotEmpty)
        Assert.assertFalse(ese.isEmpty)
    }

    [Test]
    fun test_ese_first_last()
    {
        Assert.assertEquals(ese.first, 111)
        Assert.assertEquals(ese.last, 333)
    }

    [Test]
    fun test_ese_iterator()
    {
        val it = ese.iterator()

        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 111)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 222)
        Assert.assertTrue(it.hasNext)
        Assert.assertEquals(it.next(), 333)

        Assert.assertFalse(it.hasNext)
    }



}