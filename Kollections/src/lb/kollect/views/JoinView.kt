package lb.kollect.views;

import lb.kollect.intf.List
import lb.kollect.intf.CollectionIsEmptyException
import lb.kollect.intf.EndOfCollectionReachedEmptyException


public class JoinView<T> internal (lists: Array<List<T>>) : List<T>
{
    private val innerLists = lists
    private val n = lists.size

    {
        if (n == 0)
            throw IllegalArgumentException("Attempt to join 0 lists")
    }



    override val size: Int
        get()
        {
            var total = 0
            for (list in innerLists)
                total += list.size
            return total
        }


    override val isNotEmpty: Boolean
        get()
        {
            for (list in innerLists)
                if (list.isNotEmpty)
                    return true
            return false
        }

    override val isEmpty: Boolean
        get()
        {
            for (list in innerLists)
                if (list.isNotEmpty)
                    return false
            return true
        }


    override val first: T
        get()
        {
            for (list in innerLists)
                if (list.isNotEmpty)
                    return list.first
            throw CollectionIsEmptyException("All inner lists are empty")
        }

    override val last: T
        get()
        {
            var i = n-1
            while (i >= 0)
                if (innerLists[i].isNotEmpty)
                    return innerLists[i].last
                else
                    i--
            throw CollectionIsEmptyException("All inner lists are empty")
        }


    override fun contains(item: Any): Boolean
    {
        for (list in innerLists)
            if (list.contains(item))
                return true
        return false
    }


    override fun iterator(): Iterator<T>
    {
        return MyIterator()
    }


    private class MyIterator: Iterator<T>
    {
        private var curListIndex = 0
        private var curIterator = innerLists[0].iterator()
        private var active = true


        private fun checkAvailability()
        {
            while (active) {
                if (curIterator.hasNext)
                    return
                curListIndex++
                if (curListIndex < innerLists.size)
                    curIterator = innerLists[curListIndex].iterator()
                else
                    active = false
            }
        }



        public override val hasNext: Boolean
            get()
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
                throw EndOfCollectionReachedEmptyException("No more items")

        }

    }

}
