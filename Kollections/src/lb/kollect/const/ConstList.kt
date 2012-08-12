package lb.kollect.const;

import lb.kollect.intf.*


public class ConstList<T> internal (items: Array<T>, count: Int)
          :  ConstCollection<T> (items, count),
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


    override fun indexOf(item: Any): Int
    {
        for (i in items.indices)
            if (items[i] == item)
                return i
        return -1
    }


    public fun plus(item: T): ConstList<T>
    {
        val n = count
        if (n == 0) {
            return ConstList(array<T>(item), 1)
        }
        else if (n == 1) {
            return ConstList(array<T>(items[0], item), 2)
        }
        else {
            val newItems = Array<T>(n+1, {i -> if (i == n) item else items[i]})
            return ConstList(newItems, n+1)
        }
    }

}