package lb.kollect.views;

import lb.kollect.intf.IndexingList
import lb.kollect.intf.CollectionIsEmptyException


public class JavaListView<T> (private val jlist: java.util.List<T>)
           : IndexingList<T>
{

    override val size: Int
        get() = jlist.size

    override val isEmpty: Boolean
        get() = jlist.isEmpty()

    override val isNotEmpty: Boolean
        get() = !jlist.isEmpty()


    override val first: T
        get() = if (isNotEmpty) jlist.get(0)!!
                else throw CollectionIsEmptyException("Java list is empty")

    override val last: T
        get() = if (isNotEmpty) jlist.get(jlist.size-1)!!
        else throw CollectionIsEmptyException("Java list is empty")


    override fun get(index: Int): T
    {
        return jlist.get(index)!!
    }

    override fun iterator(): Iterator<T>
    {
        return JavaIteratorView<T>(jlist.iterator()!!)
    }

    override fun contains(item: Any): Boolean
    {
        return jlist.contains(item)
    }


    override fun indexOf(item: Object): Int
    {
        return jlist.indexOf(item)
    }
}

