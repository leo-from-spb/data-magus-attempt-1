package lb.datamagus.model.core;


import org.testng.annotations.*
import lb.datamagus.model.core.A.Xxx
import org.testng.Assert


public abstract class A
{
    public abstract inner class X1()
    {
        fun a(): A = this@A
    }

    public inner class Xxx<R:A>() : X1()
    {
        var node: R? = null
    }
}


class MyTest
{
/*
    class B: A()
    {

        val x = Xxx<B>()

    }


    [Test]
    fun test_it()
    {
        val b = B()
        Assert.assertNotNull(b)
    }
*/
}
