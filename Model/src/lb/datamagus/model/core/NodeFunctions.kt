package lb.datamagus.model.core

import com.google.common.collect.ImmutableList


public val Node.descriptor : NodeDescriptor
    get() = Static.registry.get(this.javaClass.getSimpleName())


public fun Node.traverseAll(action: (node:Node)->(Unit))
{
    action(this)
    for (n in this.children())
        n.traverseAll(action)
}



public fun Node.asDelta() : Delta
{
    val d = this.descriptor
    val propDescs = d.properties
    val b = ImmutableList.builder<String>()
    for (pd in propDescs) {
    }
    throw RuntimeException ("Not implemented yet")
}