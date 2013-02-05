package lb.kotlin.utils

import java.util.Arrays
import com.google.common.collect.ImmutableList
import sun.security.jca.GetInstance

/**
 * Join view.
 * @author Leonid Bushuev from JetBrains
 */
public class ListConcat<out T> internal (lists: Array<List<T>>) : List<T>
{
    private val innerLists = lists
    private val n = lists.size

    {
        if (n == 0)
            throw IllegalArgumentException("Attempt to join 0 lists")
    }



    override fun size(): Int
    {
        var total = 0
        for (list in innerLists)
            total += list.size
        return total
    }


    val isNotEmpty: Boolean
        get()
        {
            for (list in innerLists)
                if (!list.isEmpty())
                    return true
            return false
        }

    override fun isEmpty(): Boolean
    {
        for (list in innerLists)
            if (!list.isEmpty())
                return false
        return true
    }




    override fun contains(o: Any?): Boolean
    {
        for (list in innerLists)
            if (list.contains(o))
                return true
        return false
    }


    public override fun toArray(): Array<Any?>
    {
        return toArray(array<T>())
    }


    public override fun <TT> toArray(a: Array<out TT>): Array<TT>
    {
        // ASK how to ensure that T is TT
        val b = ImmutableList.builder<T>()!!
        for (list in innerLists)
            b.addAll(list)
        return b.build()!!.toArray(a)
    }

    public override fun containsAll(c: Collection<Any?>): Boolean {
        throw UnsupportedOperationException()
    }

    public override fun get(index: Int): T
    {
        throw UnsupportedOperationException()
    }

    public override fun indexOf(o: Any?): Int {
        throw UnsupportedOperationException()
    }

    public override fun lastIndexOf(o: Any?): Int {
        throw UnsupportedOperationException()
    }

    public override fun listIterator(): ListIterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun listIterator(index: Int): ListIterator<T> {
        throw UnsupportedOperationException()
    }

    public override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        throw UnsupportedOperationException()
    }

    override fun iterator(): Iterator<T>
    {
        return MyIterator()
    }


    public override fun hashCode(): Int // ASK нафига требуется переопределять hashCode?
    {
        return innerLists.hashCode()
    }


    public override fun equals(that: Any?): Boolean // ASK нафига требуется переопределять?
    {
        return this identityEquals that
    }


    private inner class MyIterator: Iterator<T>
    {
        private var curListIndex = 0
        private var curIterator = innerLists[0].iterator()
        private var active = true


        private fun checkAvailability()
        {
            while (active) {
                if (curIterator.hasNext())
                    return
                curListIndex++
                if (curListIndex < innerLists.size)
                    curIterator = innerLists[curListIndex].iterator()
                else
                    active = false
            }
        }



        public override fun hasNext(): Boolean
        {
            checkAvailability()
            return active
        }



        public override fun next(): T
        {
            checkAvailability()
            if (active)
                return curIterator.next()
            else
                throw IllegalStateException("No more items")
        }

    }

}
