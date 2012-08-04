package lb.datamagus.model.core;

import org.jetbrains.jet.internal.com.google.common.collect.ImmutableSet
import java.util.HashSet



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


}


public open class NIP
(
        public val model:  Model,
        public val id:     Int = 0,
        public val parent: Node? = null
)

