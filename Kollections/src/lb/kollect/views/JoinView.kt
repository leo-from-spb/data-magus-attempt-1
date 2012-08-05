package lb.kollect.views;

import lb.kollect.intf.List
import lb.kollect.intf.CollectionIsEmptyException


public class JoinView<T> (vararg lists: List<T>) : List<T>
{
    val lists = lists
    val n = lists.size


    override val size: Int
        get()
        {
            var total = 0
            for (list in lists)
                total += list.size
            return total
        }


    override val isNotEmpty: Boolean
        get()
        {
            for (list in lists)
                if (list.isNotEmpty)
                    return true
            return false
        }

    override val isEmpty: Boolean
        get()
        {
            for (list in lists)
                if (list.isNotEmpty)
                    return false
            return true
        }


    override val first: T
        get()
        {
            for (list in lists)
                if (list.isNotEmpty)
                    return list.first
            throw CollectionIsEmptyException("All inner lists are empty")
        }

    override val last: T
        get()
        {
            var i = n-1
            while (i >= 0)
                if (lists[i].isNotEmpty)
                    return lists[i].last
                else
                    i++
            throw CollectionIsEmptyException("All inner lists are empty")
        }


    override fun contains(item: Any): Boolean
    {
        for (list in lists)
            if (list.contains(item))
                return true
        return false
    }
}
