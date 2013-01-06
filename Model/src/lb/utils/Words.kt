package lb.utils


public fun String.capitalize() : String
{
    if (this.isEmpty()) return this

    val c1 = this[0]
    if (Character.isLetter(c1)) {
        val cap1 = Character.toUpperCase(c1)
        val b = StringBuilder(this)
        b.setCharAt(0, cap1)
        return b.toString()
    }
    else {
        return this
    }
}
