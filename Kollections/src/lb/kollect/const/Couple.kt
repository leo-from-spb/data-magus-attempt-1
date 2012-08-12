package lb.kollect.const;

import lb.kollect.intf.IndexingList


public class Couple<T>
(
        val a: T,
        val b: T
)
: IndexingList<T>
{

    override val first: T = a
    override val last: T = b

    override val size: Int = 2
    override val isEmpty: Boolean = false
    override val isNotEmpty: Boolean = true

    override fun contains(item: Any): Boolean =
        a equals item || b equals item

    override fun get(index: Int): T
    {
        if (index == 0) return a
        if (index == 1) return b
        throw IndexOutOfBoundsException("Given index ${index} when only 0 and 1 are possible")
    }

    override fun indexOf(item: Any): Int
    {
        if (a equals item) return 0
        if (b equals item) return 1
        return -1
    }

    public fun equals (that: Couple<T>): Boolean =
            this.a equals that.a && this.b equals that.b


    public override fun iterator(): Iterator<T>
    {
        // TODO implement
        throw UnsupportedOperationException()
    }



}



