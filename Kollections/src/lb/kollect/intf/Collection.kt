package lb.kollect.intf;


public trait Collection<out T>
{

    val size: Int
        get() = 0 // ASK how to declare get() abstract?

    val isEmpty: Boolean
        get() = size == 0

    val isNotEmpty: Boolean
        get() = size > 0

    fun contains(item: Any): Boolean


}
