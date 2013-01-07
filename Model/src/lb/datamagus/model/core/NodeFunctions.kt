package lb.datamagus.model.core

import com.google.common.collect.ImmutableList


public val Node.descriptor : NodeDescriptor
    get() = Static.registry.get(this.javaClass.getSimpleName())


public fun Node.asDelta() : Delta
{
    val d = this.descriptor
    val propDescs = d.properties
    val b = ImmutableList.builder<Delta.Prop<Any?>>()
    for (pd in propDescs) {
    }
    throw RuntimeException ("Not implemented yet")
}