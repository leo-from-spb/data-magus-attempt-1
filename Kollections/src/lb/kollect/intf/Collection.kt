package lb.kollect.intf;


public trait Collection<out T> : Iterable<T>
{

    val size: Int

    val isEmpty: Boolean
        get() = size == 0

    val isNotEmpty: Boolean
        get() = size > 0

    fun contains(item: Any): Boolean

}

