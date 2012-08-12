package lb.kollect.containers;

import lb.kollect.intf.VarList
import lb.kollect.intf.IndexingList
import lb.kollect.intf.EndOfCollectionReachedEmptyException
import lb.kollect.intf.CollectionIsEmptyException
import lb.kollect.const.ConstList
import lb.kollect.const.emptyList


public class ArrList<T> : ArrContainer<T>(), VarList<T>, IndexingList<T>
{

    public override fun add(item: T)
    {
        ensureCapacityForOneItemMore();

        // ASK WTF?
        // items[count] = item

        // ASK items is always not null, how to say it to Kotlin?
        items?.set(count, item)
        count++
    }


    override val size: Int
        get() = count


    override fun contains(item: Any): Boolean =
        indexOf(item) >= 0



    override val first: T
        get() =
        if (size > 0)  items!![0]!!
        else throw CollectionIsEmptyException("Attempted to get the first items of an empty collection")



    override val last: T
        get() =
        if (size > 0)  items!![count-1]!!
        else throw CollectionIsEmptyException("Attempted to get the last items of an empty collection")


    override fun get(index: Int): T = items!![index]!!


    override fun indexOf(item: Any): Int
    {
        for (i in 0..count-1)
            if (items!![i] == item)
                return i
        return -1
    }


    public fun turnIntoConstList(): ConstList<T>
    {
        val n = count
        if (n == 0)
            {
                return emptyList()
            }
        else
            {
                val arr: Array<T> = takeAllItems() as Array<T>
                                                   // ASK the warning is really wrong
                // this.count now is 0 so use n instead of count
                return ConstList(arr, n)
            }
    }



    override fun iterator(): Iterator<T>
    {
        return MyIterator()
    }


    private class MyIterator: Iterator<T>
    {
        private var index = 0


        public override val hasNext: Boolean
            get() = index < count


        public override fun next(): T
        {
            if (index < count)
                return items!![index++]!!
            else
                throw EndOfCollectionReachedEmptyException("No more items")
        }
    }


}

