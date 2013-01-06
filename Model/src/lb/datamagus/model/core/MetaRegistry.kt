package lb.datamagus.model.core

import java.lang.reflect.*
import java.util.LinkedHashMap
import lb.utils.JavaUtils

/**
 * Meta model.
 * Known about nodes and can construct them.
 **/
public class MetaRegistry
{

    //// DESCRIPTOR CLASSES \\\\

    /**
     * Node meta-data description.
     **/
    public class NodeDescriptor
    (

            public val name: String,
            public val base: NodeDescriptor?,
            public val claß: Class<out Node>,
            public val properties: Map<String,PropertyDescriptor>

    )
    {

    }


    /**
     * Property meta-data.
     **/
    public class PropertyDescriptor
    (
            public val name: String,
            public val claß: Class<out Any?>,
            public val getter: Method,
            public val setter: Method
    )
    {

    }



    //// REGISTRY AS IS \\\\

    private val nodes: MutableMap<String,NodeDescriptor> =
            LinkedHashMap<String,NodeDescriptor>(60);



    //// REGISTRATION METHODS \\\\

    public fun<C> registerNodeDescriptors (vararg classes: Class<C>)
            where C : Node
    {
        for (claß in classes)
            registerNodeDescriptor(claß)
    }


    public fun<C> registerNodeDescriptor (claß: Class<C>)
            where C : Node
    {
        registerNodeDescriptorRecursively(claß)
    }

    public fun<C> registerNodeDescriptorRecursively (claß: Class<C>) : NodeDescriptor
            where C : Node
    {
        val name = claß.getSimpleName()

        var baseDesc: NodeDescriptor? = null

        if (name != "Node") {
            // val baseClaß = claß.getSuperClass()  // ASK WTF?
            val baseClaß = JavaUtils.getSuperClass(claß)
            if (baseClaß == null)
                throw IllegalArgumentException("The class $name is not a kind of Node class")
            val baseName = baseClaß.getSimpleName()
            baseDesc = nodes[baseName]
            if (baseDesc == null)
                baseDesc = registerNodeDescriptorRecursively(baseClaß)
        }

        val desc = prepareNodeDescriptor(claß, baseDesc)
        nodes.put(name, desc)
        return desc
    }


    public fun prepareNodeDescriptor (claß: Class<out Node>, base: NodeDescriptor?) : NodeDescriptor
    {
        val name = claß.getSimpleName()

        val properties = LinkedHashMap<String,PropertyDescriptor>()
        if (base != null)
            properties.putAll(base.properties)

        for (field in claß.getDeclaredFields()) {
            if (Modifier.isVolatile(field.getModifiers()))
                continue
            val fname = field.getName()!!
            val fclaß = field.getType()!!
            val fgetter = try { claß.getDeclaredMethod("get"+fname.capitalize()) } catch (x:Exception) {null}
            if (fgetter == null || Modifier.isPrivate(fgetter.getModifiers()))
                continue
            val fsetter = try { claß.getDeclaredMethod("set"+fname.capitalize(), fclaß) } catch (x:Exception) {null}
            if (fsetter == null || Modifier.isPrivate(fsetter.getModifiers()))
                continue
            val property = PropertyDescriptor(fname, fclaß, fgetter, fsetter)
            properties.put(fname, property)
        }

        val desc = NodeDescriptor(name, base, claß, properties)
        return desc
    }





    //// ACCESSORS \\\\

    public fun descriptionFor(name: String) : NodeDescriptor
    {
        val foundDescriptor = nodes[name]
        if (foundDescriptor != null)
            return foundDescriptor
        else
            throw IllegalArgumentException("Node class $name is not registered.")
    }


}
