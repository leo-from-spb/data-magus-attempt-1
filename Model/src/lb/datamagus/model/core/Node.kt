package lb.datamagus.model.core;

import com.google.common.collect.ImmutableList
import lb.datamagus.model.core.exceptions.*
import lb.kotlin.utils.emptyList

/**
* The most abstract class of the model hierarchy.
**/
public abstract class Node (nip: NIP)
{

    public val model: Model = nip.model

    public val id: Int = if (nip.id > 0) nip.id else nip.model.takeNewId()

    public val parent: Node? = nip.parent

    public var dropt: Boolean = false
        private set(value) {$dropt = value}



    //// INITIALIZATION AND FINALIZATION \\\\

    // constructor
    {
        nip.model.registerNode(this)
    }

    // destructor
    public fun drop()
    {
        model.unregisterNode(this)
        dropt = true
    }


    protected fun makeFamily<C:Node>(childClass: Class<C>): Family<C> = Family(childClass)
    protected fun makeRef<R:Node>(): Ref<R> = Ref<R>()
    protected fun makeRefs<R:Node>(): Refs<R> = Refs<R>()



    //// COMMON METHODS \\\\

    open fun children() : List<Node>
    {
        return emptyList()
    }


    public fun toString(): String
    {
        return "${this.javaClass.getSimpleName()}:${id}"
    }


    //// REFERENCES \\\\

    private val refPoints = java.util.HashSet<RefPoint>(16)
    private val refNodes = java.util.HashSet<Node>(16)

    public val references: Set<Node> = refNodes


    private fun addRefBy(refPoint: RefPoint)
    {
        refPoints.add(refPoint)
        refNodes.add(refPoint.refBy())
    }

    private fun removeRefBy(refPoint: RefPoint)
    {
        refPoints.remove(refPoint)
        var stillReferenced = false
        val refNode = refPoint.refBy()
        for (p in refPoints)
            if (p.refBy() == refNode)
                { stillReferenced = true; break }
        if (!stillReferenced)
            refNodes.remove(refNode)
    }




    //// FAMILY CLASS \\\\

    public open class Family<C:Node>(val childClass: Class<C>)
                    : List<C>
    {

        private val children = java.util.ArrayList<C>(16)

        private val constructor = childClass.getConstructors()[0]


        internal fun add(child: C)
        {
            children.add(child)
        }


        public fun create(init: C.() -> Unit): C
        {
            val nip = NIP(model = model, parent = this@Node)
            val newChild = constructor.newInstance(nip)!! as C
            children.add(newChild)
            newChild.init()
            return newChild
        }



        //// LIST DELEGATES \\\\


        override fun size(): Int
        {
            return children.size
        }


        override fun contains(o: Any?): Boolean = children.contains(o)


        override fun get(index: Int): C = children.get(index)

        override fun indexOf(item: Any?): Int = children.indexOf(item)


        public override fun iterator(): Iterator<C>
        {
            throw RuntimeException("Not implemented yet")
        }


        public override fun isEmpty() = children.isEmpty()

        public override fun toArray(): Array<Any?> {
            throw UnsupportedOperationException()
        }
        public override fun <T> toArray(a: Array<out T>): Array<T> {
            throw UnsupportedOperationException()
        }
        public override fun containsAll(c: Collection<Any?>): Boolean {
            throw UnsupportedOperationException()
        }
        public override fun lastIndexOf(o: Any?): Int {
            throw UnsupportedOperationException()
        }
        public override fun listIterator(): ListIterator<C> {
            throw UnsupportedOperationException()
        }
        public override fun listIterator(index: Int): ListIterator<C> {
            throw UnsupportedOperationException()
        }
        public override fun subList(fromIndex: Int, toIndex: Int): List<C> {
            throw UnsupportedOperationException()
        }


        public override fun equals(that: Any?): Boolean {
            return this identityEquals that
        }

        public override fun hashCode(): Int {
            return children.hashCode()
        }
    }


    //// REFERENCE CLASSES \\\\


    public open class RefPoint()
    {
        public fun refBy(): Node = this@Node
    }


    public class Ref<R:Node>() : RefPoint()
    {
        public var node: R? = null
            set(newNode)
            {
                if (newNode == $node)
                    return
                if (newNode != null && newNode.model != this@Node.model)
                    throw AlienNodeException("Node ${newNode} is from another model")

                val oldNode = $node
                if (oldNode != null)
                {
                    $node = null
                    oldNode.removeRefBy(this)
                }

                if (newNode != null)
                {
                    $node = newNode
                    newNode.addRefBy(this)
                }
            }

        public val id: Int?
            get() = node?.id

        public val exists: Boolean
            get() = node != null
    }


    public class Refs<R:Node>() : RefPoint()
    {
        public var nodes: List<R> = emptyList()
            set(newNodes)
            {
                nodes = ImmutableList.copyOf(newNodes)!!
/*
                val d = diff(nodes, newNodes)
                for (x in d.a)
                    x.removeRefBy(this)
                for (x in d.b)
                    x.addRefBy(this)
*/
            }
    }



}



