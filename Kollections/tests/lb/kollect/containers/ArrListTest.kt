package lb.kollect.containers;

import org.testng.annotations.*
import org.testng.Assert
import lb.kollect.intf.VarList


class ArrListTest
{
    [Test]
    fun test_0()
    {
        val list: VarList<Long> = ArrList<Long>()

        Assert.assertEquals(list.size, 0)
        Assert.assertEquals(list.isEmpty, true)
        Assert.assertEquals(list.isNotEmpty, false)
    }

    [Test]
    fun test_add_3()
    {
        val list = ArrList<Long>()
        list.add(111111111111)
        list.add(222222222222)
        list.add(333333333333)

        Assert.assertEquals(list.size, 3)
        Assert.assertEquals(list.isEmpty, false)
        Assert.assertEquals(list.isNotEmpty, true)
        Assert.assertEquals(list.first, 111111111111)
        Assert.assertEquals(list.last, 333333333333)
        Assert.assertEquals(list[0], 111111111111)
        Assert.assertEquals(list[1], 222222222222)
        Assert.assertEquals(list[2], 333333333333)
    }


    [Test]
    fun test_turnIntoConstList_0()
    {
        val buf = ArrList<String>()

        Assert.assertEquals(buf.size, 0)

        val list = buf.turnIntoConstList()

        Assert.assertEquals(list.size, 0)
        Assert.assertEquals(buf.size, 0)
    }

    [Test]
    fun test_turnIntoConstList_2()
    {
        val buf = ArrList<Int>()
        buf.add(1111)
        buf.add(2222)

        Assert.assertEquals(buf.size, 2)

        val list = buf.turnIntoConstList()

        Assert.assertEquals(list.size, 2)
        Assert.assertEquals(list[0], 1111)
        Assert.assertEquals(list[1], 2222)

        Assert.assertEquals(buf.size, 0)

        // verify that list is not changed when the buffer is changed

        buf.add(3333)
        buf.add(4444)

        Assert.assertEquals(list[0], 1111)
        Assert.assertEquals(list[1], 2222)
    }

}



