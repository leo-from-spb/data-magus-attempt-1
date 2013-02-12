package lb.datamagus.model.core

import java.lang.reflect.Method

/**
 * Node meta-data description.
 **/
public data class NodeDescriptor
(

        public val name: String,
        public val base: NodeDescriptor?,
        public val claß: Class<out Node>,
        public val properties: Map<String,PropertyDescriptor>

)
{
    public fun get(name: String) : PropertyDescriptor
    {
        val p = properties[name]
        if (p != null)
            return p
        else
            throw IllegalArgumentException("Node of type ${this.name} has no property $name.")
    }

}


/**
 * Property meta-data.
 **/
public data class PropertyDescriptor
(
        public val name: String,
        public val ptype: PropertyType,
        public val claß: Class<out Any?>,
        public val getter: Method,
        public val setter: Method?
)
{

}


public enum class PropertyType
{
        Bool
        Int
        Str
        Ref
}