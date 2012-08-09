package lb.kollect.const;

import lb.kollect.intf.CollectionIsEmptyException
import lb.kollect.intf.IndexingList
import lb.kollect.intf.Set

public class ConstNothing<T> : Set<T>, IndexingList<T>
{

    override val size: Int = 0
    override val isNotEmpty: Boolean = false
    override val isEmpty: Boolean = true

    override fun contains(item: Any): Boolean = false

    override val first: T
        get() { throw CollectionIsEmptyException("Attempt to get the first of ConstNothing") }

    override val last: T
        get() { throw CollectionIsEmptyException("Attempt to get the last item of ConstNothing") }


    override fun get(index: Int): T
    {
        throw CollectionIsEmptyException("Attempt to get the ${index}-th item of ConstNothing")
    }


    override fun indexOf(item: Object): Int = -1


    public override fun iterator(): Iterator<T> = ZeroIterator()


    private class ZeroIterator<T> : Iterator<T>
    {

        public override fun next(): T
        {
            throw CollectionIsEmptyException("Attempt to get the next item of ConstNothing")
        }

        public override val hasNext: Boolean = false
    }


}
