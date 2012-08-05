package lb.kollect.views;



public class JavaIteratorView<T> (private val jit: java.util.Iterator<T>)
           : Iterator<T>
{

    public override val hasNext: Boolean
        get() = jit.hasNext()


    public override fun next(): T = jit.next()!!

}
