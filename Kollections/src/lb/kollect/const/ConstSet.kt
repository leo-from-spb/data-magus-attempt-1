package lb.kollect.const;

import lb.kollect.intf.Set


public class ConstSet<T>(items: Array<T>, count: Int)
           : ConstCollection<T>(items, count),
             Set<T>
{
    val hashes = IntArray(size)
    val hashed = size > 3;

    {
        /* ASK how to get hash
        for (i in items.indices)
            hashes[i] = items[i].hashCode()
        */
    }

    // TODO hash array if need


}




