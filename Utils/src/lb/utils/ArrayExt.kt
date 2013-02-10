package lb.utils


public fun<T> Array<T>.toStr(delimiter: String = ",",
                             prefix: String = "",
                             sufix: String = "",
                             empty: String = "") : String
{
    if (this.isEmpty())
        return empty;
    val b = StringBuilder()
    b.append(prefix)
    var first = true
    for (item in this) {
        if (first)
            first = false
        else
            b.append(delimiter)
        b.append(item);
    }
    b.append(sufix)
    return b.toString()
}

