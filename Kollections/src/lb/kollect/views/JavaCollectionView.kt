package lb.kollect.views;

import lb.kollect.intf.Collection

public open class JavaCollectionView<T> (private val jCollection: java.util.Collection<T>)
           : Collection<T>
{

    override val size: Int
        get() = jCollection.size

    override val isEmpty: Boolean
        get() = jCollection.isEmpty()

    override val isNotEmpty: Boolean
        get() = !jCollection.isEmpty()


    override fun iterator(): Iterator<T>
    {
        return JavaIteratorView<T>(jCollection.iterator()!!)
    }

    override fun contains(item: Any): Boolean
    {
        return jCollection.contains(item)
    }

}

