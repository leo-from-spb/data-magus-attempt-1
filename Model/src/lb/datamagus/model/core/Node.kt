package lb.datamagus.model.core;

import lb.kollect.const.*
import lb.kollect.intf.*
import lb.kollect.views.*

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


    //// COMMON METHODS \\\\

    open fun children() : List<Node>
    {
        return empty()
    }



    //// FAMILY CLASS \\\\

    public open class Family<C:Node>(val childClass: Class<C>)
                    : IndexingList<C>
    {

        private val children = java.util.ArrayList<C>(16)

        private val constructor = childClass.getConstructors()!![0]!!


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


        override val size: Int
            get() = children.size

        override val first: C
            get() = children[0]!!

        override val last: C
            get() = children[children.size()-1]!!

        override fun contains(item: Any): Boolean = children.contains(item)


        override fun get(index: Int): C = children.get(index)!!

        override fun indexOf(item: Object): Int = children.indexOf(item)

        public override fun iterator(): Iterator<C> = JavaIteratorView(children.iterator()!!)

    }




}



