package lb.kollect.const;

import lb.kollect.intf.Collection
import lb.kollect.intf.EndOfCollectionReachedEmptyException


public abstract class ConstCollection<T> (protected val items: Array<T>)
                    : Collection<T>
{

    override val size: Int = items.size


    override fun contains(item: Any): Boolean
    {
        for (i in 0..size-1)
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
            get() = index < size


        public override fun next(): T
        {
            if (index < size)
                return items[index++]
            else
                throw EndOfCollectionReachedEmptyException("No more items")
        }
    }


}

