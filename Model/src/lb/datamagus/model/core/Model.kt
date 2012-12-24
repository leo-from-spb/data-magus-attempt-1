package lb.datamagus.model.core

import java.util.concurrent.atomic.AtomicInteger
import java.util.HashMap
import lb.datamagus.model.core.exceptions.NoSuchNodeException
import lb.datamagus.model.core.exceptions.NodeClassMismatchException
import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableList


public class Model
{

    //// INTERNAL STATE \\\\

    private val idSequence = AtomicInteger(0)

    private val allNodes = HashMap<Int,Node>(1024)

    private var projectRoot: ProjectRoot? = null




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

    public fun node<N:Node>(id: Int): N
    {
        val x = allNodes[id]
        when (x) {
            null -> throw NoSuchNodeException("Node ${id} not found")
            is N -> return x
            //ASK
            //else -> throw NodeClassMismatchException("Requested node ${id} of class ${T.javaClass.getSimpleName()} but this node is ${x.javaClass.getSimpleName()}")
            else -> throw NodeClassMismatchException("Requested node ${id}: type mismatch")
        }
    }

    public fun hasNode(id: Int): Boolean = allNodes.containsKey(id)

    public fun viewAllNodes() : Collection<Node> = allNodes.values()



    public fun createProjectRoot() : ProjectRoot
    {
        if (projectRoot == null) {
            val r = ProjectRoot(NIP(model = this))
            projectRoot = r
            return r
        }
        else {
            throw IllegalStateException("The project root already exists.")
        }

    }

    public fun getProjectRoot() : ProjectRoot
    {
        val r = projectRoot  // ASK how to simplify
        if (r != null)
            return r
        else
            throw IllegalStateException("The project root is not created yet")
    }





    //// CLEANUP PROCEDURES \\\\

    public fun removeAllNodes()
    {
        // ASK how to create an empty array for the specified type of items ?
        // val nodes = allNodes.values().toArray(Array<Node>(n))
        val nodes = ImmutableList.copyOf(allNodes.values())!!
        allNodes.clear()
        for (node in nodes.iterator()!!)
            if (!node.dropt)
                node.drop()
    }


}



