package lb.kollect.const;

import lb.kollect.intf.*


public class ConstList<T> internal (val items: Array<T>) : List<T>
{

    //// INITIALIZERS \\\\

    class object
    {
        public fun<T> of(vararg items: T): ConstList<T>
                {
                    return ConstList(items)
                }
    }



    //// IMPLEMENTATION \\\\

    override val size: Int = items.size


    override val first: T =
            if (size > 0)  items[0]
            else throw CollectionIsEmptyException("Attempted to get the first items of an empty collection")



    override val last: T =
            if (size > 0)  items[size-1]
            else throw CollectionIsEmptyException("Attempted to get the last items of an empty collection")


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

}