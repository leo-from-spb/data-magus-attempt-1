package lb.datamagus.model.core

import java.lang.reflect.*
import java.util.LinkedHashMap
import lb.utils.JavaUtils
import lb.kotlin.utils.JavaClass


/**
 * Meta model.
 * Known about nodes and can construct them.
 **/
public class MetaRegistry
{

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
            val ptype = fclaß.propertyType()
            if (ptype == null)
                continue // TODO log it
            val pname = fname.capitalize()
            val pgetter = try { claß.getDeclaredMethod("get"+pname) } catch (x:Exception) {null}
            if (pgetter == null || Modifier.isPrivate(pgetter.getModifiers()))
                continue
            val psetter = try { claß.getDeclaredMethod("set"+pname, fclaß) } catch (x:Exception) {null}
            if (psetter == null || Modifier.isPrivate(psetter.getModifiers()))
                continue
            val property = PropertyDescriptor(pname, ptype, fclaß, pgetter, psetter)
            properties.put(pname, property)
        }

        val desc = NodeDescriptor(name, base, claß, properties)
        return desc
    }


    private fun Class<*>.propertyType() : PropertyType?
    {
        val name = this.getSimpleName();
        if (name == "boolean" || name == "Boolean") return PropertyType.Bool
        if (name == "int" || name == "Integer") return PropertyType.Int
        if (name == "String") return PropertyType.Str
        return null
    }



    //// ACCESSORS \\\\

    public fun get(name: String) : NodeDescriptor
    {
        val foundDescriptor = nodes[name]
        if (foundDescriptor != null)
            return foundDescriptor
        else
            throw IllegalArgumentException("Node class $name is not registered.")
    }


}
