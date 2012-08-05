package lb.kollect.intf;


public trait List<out T> : Collection<T>
{
    val first: T

    val last: T
}