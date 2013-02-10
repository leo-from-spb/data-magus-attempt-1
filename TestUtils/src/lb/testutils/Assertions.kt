package lb.testutils

import org.testng.Assert
import kotlin.test.fail
import lb.utils
import lb.utils.toStr
import lb.utils.ObjectsAreEqual


fun<T> T._equals_(that: T)
{
    Assert.assertEquals(this, that);
}

fun<T> T._not_equals_(that: T)
{
    Assert.assertNotEquals(this, that);
}


fun<T> T._same_as_(that: T)
{
    Assert.assertSame(this, that);
}


inline fun Any?._not_null_()
{
    if (this == null)
        Assert.fail("Expected not null but got null.");
}

fun Any?._null_()
{
    if (this != null)
        Assert.fail("Expected null but got $this");
}


fun<T> Collection<T>._contains_(item: T)
{
    val kind = when (this)
        {
        is Set<*> -> "set"
        is List<*> -> "list"
        else -> "collection"
        }

    if (this.isEmpty())
    {
        Assert.fail("The given $kind expected to contain $item but it is empty.");
    }

    if (!this.contains(item))
    {
        val content = this.makeString(",", "[","]", 20, "and more... (total ${this.size()} elements")
        val msg = "The $kind $content expected to contain $item but it doesn't."
        Assert.fail(msg)
    }
}


fun<T> T._in_(bunch: Collection<T>)
{
    if (!bunch.contains(this))
    {
        val kind = when (bunch)
        {
            is Set<*> -> "set"
            is List<*> -> "list"
            else -> "collection"
        }
        val content = bunch.makeString(",", "[","]", 20, "and more... (total ${bunch.size()} elements")
        val msg = "The item $this is expected to be in the $kind $content but it doesn't."
        Assert.fail(msg)
    }
}


fun<T> T._not_in_(bunch: Collection<T>)
{
    if (bunch.contains(this))
    {
        val kind = when (bunch)
        {
            is Set<*> -> "set"
            is List<*> -> "list"
            else -> "collection"
        }
        val content = bunch.makeString(",", "[","]", 20, "and more... (total ${bunch.size()} elements")
        val msg = "The item $this is expected to NOT be in the $kind $content but it does."
        Assert.fail(msg)
    }
}


fun Int._greater_(bound: Int)
{
    if (!(this > bound))
        Assert.fail("Got $this when expected a value greater than $bound");
}

fun Int._greater_or_equal_(bound: Int)
{
    if (!(this >= bound))
        Assert.fail("Got $this when expected a value greater than or equal to $bound");
}

fun Int._less_(bound: Int)
{
    if (!(this < bound))
        Assert.fail("Got $this when expected a value less than $bound");
}

fun Int._less_or_equal_(bound: Int)
{
    if (!(this <= bound))
        Assert.fail("Got $this when expected a value less than or equal to $bound");
}


fun Boolean._true_()
{
    Assert.assertTrue(this);
}

fun Boolean._false_()
{
    Assert.assertFalse(this);
}


fun<T> List<T>._list_(vararg exp: T)
{
    val n = exp.size;
    val got = this.toArray();
    if (this.size() != n)
        fail("Got ${this} when expected ${exp.toStr(", ","[", "]", "<an empty list>")}. Size of lists are different.")
    var mismatches = 0;
    for (val i in 0..n-1)
        if (!ObjectsAreEqual(this[i], exp[i]))
            mismatches++
    if (mismatches > 0)
        fail("Got ${this} when expected ${exp.toStr(", ","[", "]", "<an empty list>")}. Found $mismatches mismatches.")
}



