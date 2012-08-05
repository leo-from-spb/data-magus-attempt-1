package lb.kollect.intf;



public trait IndexingList<out T>: List<T>
{

    fun get(index: Int): T


    open fun indexOf(item: Object): Int


}



