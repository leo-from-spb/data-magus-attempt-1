package org.jetbrains.datamagus.model.content

import com.jetbrains.datamagus.model.ancillary.Family
import com.jetbrains.datamagus.model.content.AbElement

abstract class ImmElement : AbElement
{

    final override val id: Int

    constructor(id: Int)
    {
        this.id = id
    }

    override fun hashCode() = id
    override fun equals(other: Any?) = this === other // implemented just to mute the warning

    override fun toString() = "$id:${this.javaClass.simpleName}"
}



sealed class ImmFamily<out E: ImmElement> : Family<E>


fun <E: ImmElement> familyOf(vararg elements: E): ImmFamily<E> =
        when (elements.size) {
            0    -> ImmEmptyFamily
            1    -> ImmSingletonFamily(elements[0])
            else -> ImmMultFamily(elements, copy = true)
        }

fun <E: ImmElement> familyOf(elements: Collection<E>): ImmFamily<E> =
        when (elements.size) {
            0    -> ImmEmptyFamily
            1    -> ImmSingletonFamily(elements.first())
            else -> ImmMultFamily(elements.toTypedArray<ImmElement>(), copy = false)
        }


object ImmEmptyFamily : ImmFamily<Nothing>()
{
    override fun get(index: Int): Nothing = nothing()
    override val first: Nothing get() = nothing()
    override val last: Nothing get() = nothing()
    override val size: Int = 0
    override fun isEmpty() = true
    override fun isNotEmpty() = false
    override fun contains(element: @UnsafeVariance Nothing) = false
    override fun containsAll(elements: Collection<@UnsafeVariance Nothing>) = false
    override fun iterator(): Iterator<Nothing> = EmptyIterator()

    private class EmptyIterator : Iterator<Nothing> {
        override fun hasNext() = false
        override fun next(): Nothing = nothing()
    }
}


@Suppress("nothing_to_inline")
private inline fun nothing(): Nothing { throw IllegalStateException("The family is empty") }


class ImmSingletonFamily<out E: ImmElement>
(
        private val theElement: E
)
    : ImmFamily<E>()
{

    override fun get(index: Int): E =
            if (index == 0) theElement
            else throw IndexOutOfBoundsException("Index is $index when the family size is 1")

    override val first: E get() = theElement
    override val last: E get() = theElement
    override val size: Int = 1
    override fun isEmpty() = false
    override fun isNotEmpty() = true
    override fun contains(element: @UnsafeVariance E) = this.theElement === element
    override fun containsAll(elements: Collection<@UnsafeVariance E>) = elements.size == 1 && elements.contains(this.theElement)
    override fun iterator(): Iterator<E> = SingletonIterator()

    private inner class SingletonIterator : Iterator<E> {
        private var passed = false
        override fun hasNext() = !passed
        override fun next(): E =
                if (!passed) { passed = true; theElement }
                else throw IllegalStateException("The only element is passed, no more elements.")
    }
}


@Suppress("unchecked_cast")
class ImmMultFamily<out E: ImmElement> : ImmFamily<E>
{

    private val elements: Array<out ImmElement>


    constructor(elements: Array<E>) : this(elements, copy = true)

    internal constructor(elements: Array<out ImmElement>, copy: Boolean) : super()
    {
        if (copy)
        {
            val n = elements.size
            val a: Array<out ImmElement?> = arrayOfNulls(n)
            System.arraycopy(elements, 0, a, 0, n)
            @Suppress("unchecked_cast")
            this.elements = a as Array<out ImmElement>
        }
        else
        {
            this.elements = elements
        }
    }

    override fun get(index: Int): E =
            if (index >= 0 && index < elements.size) elements[index] as E
            else throw IndexOutOfBoundsException("Index is $index when the family size is ${elements.size}")

    override val first: E get() = elements[0] as E
    override val last: E get() = elements[elements.size-1] as E
    override val size: Int get() = elements.size
    override fun isEmpty() = false
    override fun isNotEmpty() = true
    override fun contains(element: @UnsafeVariance E) = elements.contains(element)

    override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean {
        for (e in elements) if (e !in this.elements) return false
        return true
    }

    override fun iterator(): Iterator<E> = elements.iterator() as Iterator<E>
}
