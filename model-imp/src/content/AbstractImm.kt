package org.jetbrains.datamagus.model.content

import com.jetbrains.datamagus.model.ancillary.Family
import com.jetbrains.datamagus.model.content.AbElement

abstract class ImmElement : AbElement
{

    override val id: Int


    constructor(id: Int)
    {
        this.id = id
    }

}



sealed class ImmFamily<out E: ImmElement> : Family<E>
{

}


class ImmEmptyFamily<out E: ImmElement> : ImmFamily<ImmElement>()
{
    override val size: Int = 0
    override fun isEmpty() = true
    override fun isNotEmpty() = false
    override fun contains(element: ImmElement) = false
    override fun containsAll(elements: Collection<ImmElement>) = false
    override fun iterator(): Iterator<Nothing> = EmptyIterator()

    private class EmptyIterator : Iterator<Nothing> {
        override fun hasNext() = false
        override fun next(): Nothing { throw IllegalStateException("The family is empty") }
    }
}


class ImmSingletonFamily<out E: ImmElement>
(
        private val theElement: E
)
    : ImmFamily<ImmElement>()
{

    override val size: Int = 1
    override fun isEmpty() = false
    override fun isNotEmpty() = true
    override fun contains(element: ImmElement) = this.theElement === element
    override fun containsAll(elements: Collection<ImmElement>) = elements.size == 1 && elements.contains(this.theElement)
    override fun iterator(): Iterator<ImmElement> = SingletonIterator()

    private inner class SingletonIterator : Iterator<E> {
        private var passed = false
        override fun hasNext() = !passed
        override fun next(): E =
                if (!passed) { passed = true; theElement }
                else throw IllegalStateException("The only element is passed, no more elements.")
    }
}


class ImmMultFamily<out E: ImmElement> : ImmFamily<ImmElement>
{

    private val elements: Array<E>

    internal constructor(elements: Array<E>, copy: Boolean) : super()
    {
        if (copy)
        {
            val n = elements.size
            val a: Array<ImmElement?> = arrayOfNulls(n)
            System.arraycopy(elements, 0, a, 0, n)
            @Suppress("unchecked_cast")
            this.elements = a as Array<E>
        }
        else
        {
            this.elements = elements
        }
    }

    override val size: Int get() = elements.size
    override fun isEmpty() = false
    override fun isNotEmpty() = true
    override fun contains(element: ImmElement) = elements.contains(element)
    override fun containsAll(elements: Collection<ImmElement>) = elements.containsAll(elements)
    override fun iterator(): Iterator<ImmElement> = elements.iterator()
}

