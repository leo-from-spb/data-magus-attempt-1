package lb.kollect.const;

import lb.kollect.intf.Collection
import lb.kollect.intf.EndOfCollectionReachedEmptyException


public abstract class ConstCollection<T> (protected val items: Array<T>,
                                          protected val count: Int)
                    : Collection<T>
{

    override val size: Int = count


    override fun contains(item: Any): Boolean
    {
        for (i in 0..count-1)
        {
            val x = items[i]
            if (x == item)
                return true
        }

        return false
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
                return items[index++]
            else
                throw EndOfCollectionReachedEmptyException("No more items")
        }
    }

    // ASK how to overload ==
    public fun equals(that: ConstCollection<T>): Boolean
    {
        val thisItems = this.items
        val thatItems = that.items

        if (thisItems.identityEquals(thatItems))
            return true
        if (this.size != that.size)
            return false
        for (i in items.indices)
            if (!(thisItems[i] equals thatItems[i]))
                return false
        return true
    }

}

