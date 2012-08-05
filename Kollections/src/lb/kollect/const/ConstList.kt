package lb.kollect.const;

import lb.kollect.intf.*


public class ConstList<T> (items: Array<T>)
          :  ConstCollection<T> (items),
             IndexingList<T>,
             List<T>
{


    override val first: T
        get() =
            if (size > 0)  items[0]
            else throw CollectionIsEmptyException("Attempted to get the first items of an empty collection")



    override val last: T
        get() =
            if (size > 0)  items[size-1]
            else throw CollectionIsEmptyException("Attempted to get the last items of an empty collection")


    override fun get(index: Int): T = items[index]


    override fun indexOf(item: Object): Int
    {
        for (i in items.indices)
            if (items[i] == item)
                return i
        return -1
    }

}