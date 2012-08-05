package lb.datamagus.model.core;

import java.util.HashSet
import java.util.List
import java.util.Collections


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


    // constructor
    {
        nip.model.registerNode(this)
    }


    // destructor
    fun drop()
    {
        model.unregisterNode(this)
        dropt = true
    }



    //// FAMILY \\\\

    open fun children() : List<Node>
    {
        return Collections.emptyList<Node>()!!
    }


}


public open class NIP
(
        public val model:  Model,
        public val id:     Int = 0,
        public val parent: Node? = null
)

