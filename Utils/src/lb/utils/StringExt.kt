package lb.utils


public fun String?.nullify(): String?
{
    return if (this != null && this.length > 0) this; else null
}


