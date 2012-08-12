package lb.kollect.views;

import lb.kollect.intf.CollectionIsEmptyException
import lb.kollect.intf.IndexingList

public class JavaListView<T> (private val jList: java.util.List<T>)
           : JavaCollectionView<T> (jList),
             IndexingList<T>
{

    override val first: T
        get() = if (isNotEmpty) jList.get(0)!!
                else throw CollectionIsEmptyException("Java list is empty")

    override val last: T
        get() = if (isNotEmpty) jList.get(jList.size-1)!!
        else throw CollectionIsEmptyException("Java list is empty")


    override fun get(index: Int): T
    {
        return jList.get(index)!!
    }

    override fun indexOf(item: Any): Int
    {
        return jList.indexOf(item)
    }
}

