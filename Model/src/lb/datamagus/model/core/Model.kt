package lb.datamagus.model.core;

import java.util.concurrent.atomic.AtomicInteger
import java.util.HashMap
import lb.datamagus.model.core.exceptions.NoSuchNodeException
import lb.datamagus.model.core.exceptions.NodeClassMismatchException
import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableList


public class Model
{

    private val idSequence = AtomicInteger(0)

    private val allNodes = HashMap<Int,Node>(1024)



    //// INTERNAL PROTOCOL PROCEDURES \\\


    fun takeNewId() : Int =  idSequence.incrementAndGet()


    fun registerNode(node: Node)
    {
        allNodes.put(node.id, node)
    }

    fun unregisterNode(node: Node)
    {
        allNodes.remove(node.id)
    }



    //// PUBLIC UTILITY FUNCTIONS \\\\

    public val countNodes: Int
           get() = allNodes.size

    public fun node<T:Node>(id: Int): T
    {
        val x = allNodes[id]
        when (x) {
            is null -> throw NoSuchNodeException("Node ${id} not found")
            is T -> return x
            //ASK
            //else -> throw NodeClassMismatchException("Requested node ${id} of class ${T.javaClass.getSimpleName()} but this node is ${x.javaClass.getSimpleName()}")
            else -> throw NodeClassMismatchException("Requested node ${id}: type mismatch")
        }
    }

    public fun hasNode(id: Int): Boolean = allNodes.containsKey(id)


    //// CLEANUP PROCEDURES \\\\

    public fun removeAllNodes()
    {
        //val n = allNodes.size();
        // ASK
        // val nodes = allNodes.values().toArray(Array<Node>(n))
        val nodes = ImmutableList.copyOf(allNodes.values())
        allNodes.clear()
        for (node in nodes)
            if (node != null && !node.dropt)  // ASK node always != null
                node.drop()
    }


}



