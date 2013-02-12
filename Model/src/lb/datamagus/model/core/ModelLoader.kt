package lb.datamagus.model.core

import com.google.common.collect.ImmutableList
import java.util.ArrayList
import java.util.Date
import lb.datamagus.model.core.Delta.Prop
import lb.datamagus.model.core.Node.Ref
import lb.utils.nullify

public class ModelLoader
{

    public fun exportWholeModelAsModification(model: AccModel, modifName: String): Modification
    {
        // prepare the modification details
        val modifTimestamp = Date()
        val deltas = ImmutableList.builder<Delta>()!!

        // get all nodes in the right order
        val root = model.getProjectRoot()
        val nodes = ArrayList<Node>(model.countNodes)
        root.traverseAll { node -> nodes.add(node) }

        // iterate all nodes
        for (node in nodes) {
            val delta = exportNode(node)
            deltas add delta
        }

        // ok
        val m = Modification(modifName, modifTimestamp, deltas.build()!!)
        return m;
    }


    public fun exportNode(node: Node): Delta
    {
        val nodeClassName = node.javaClass.getSimpleName()
        val nodeDescriptor = Static.registry.get(nodeClassName)
        val props = ImmutableList.builder<Prop>()!!

        for (pd in nodeDescriptor.properties.values()) {
            val value = exportProperty(node, pd)
            if (value == null)
                continue
            val prop = Prop(pd.name, null, value)
            props add prop
        }

        val delta = Delta(node.id, Delta.Kind.Create, nodeClassName, node.displayName, props.build()!!)
        return delta
    }


    fun exportProperty(node: Node, pd: PropertyDescriptor): String?
    {
        return when (pd.ptype) {
                   PropertyType.Bool -> {
                       val b = getBoolValue(node, pd)
                       if (b) "+"
                       else null
                   }
                   PropertyType.Int -> {
                       val v = getIntValue(node, pd)
                       if (v != 0) Integer.toString(v)
                       else null
                   }
                   PropertyType.Str -> {
                       getStrValue(node, pd).nullify()
                   }
                   PropertyType.Ref -> {
                       val theRefNodeId = getRefValue(node, pd)
                       if (theRefNodeId != null) return "#${theRefNodeId}"; else null
                   }
                   else ->
                       throw RuntimeException("Cannot export node $node: unknown type of property ${pd.name}.")
               }
    }


    fun getBoolValue(node: Node, pd: PropertyDescriptor): Boolean
    {
        val x = pd.getter.invoke(node);
        if (x == null)
            throw TypeMismatchException("Could not read property ${pd.name} from $node: got null when expected a boolean value.")
        if (x is Boolean)
            return x
        else
            throw TypeMismatchException("Could not read property ${pd.name} from $node: got a value of type ${x.javaClass.getSimpleName()} when expected a boolean value.")
    }

    fun getIntValue(node: Node, pd: PropertyDescriptor): Int
    {
        val x = pd.getter.invoke(node);
        if (x == null)
            throw TypeMismatchException("Could not read property ${pd.name} from $node: got null when expected an integer value.")
        if (x is Int)
            return x
        else
            throw TypeMismatchException("Could not read property ${pd.name} from $node: got a value of type ${x.javaClass.getSimpleName()} when expected an integer value.")
    }

    fun getStrValue(node: Node, pd: PropertyDescriptor): String?
    {
        val x = pd.getter.invoke(node);
        if (x == null)
            return null;
        if (x is String)
            return x
        else
            throw TypeMismatchException("Could not read property ${pd.name} from $node: got a value of type ${x.javaClass.getSimpleName()} when expected a string value.")
    }

    fun getRefValue(node: Node, pd: PropertyDescriptor): Int?
    {
        val theRefObject = pd.getter.invoke(node);
        theRefObject!!
        if (theRefObject is Ref<*>) {
            val theRef: Ref<*>  = theRefObject
            return theRef.id
        }
        return 0
    }


    public class TypeMismatchException(message: String) : RuntimeException(message)
}