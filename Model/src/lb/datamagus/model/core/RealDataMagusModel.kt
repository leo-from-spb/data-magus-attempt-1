package lb.datamagus.model.core

import com.google.common.collect.ImmutableList
import java.util.HashMap
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write
import lb.datamagus.model.core.exceptions.NoSuchNodeException
import lb.datamagus.model.core.exceptions.NodeClassMismatchException


/**
 * Main model implementation.
 **/
internal class RealDataMagusModel : WorkModel, IntModel, ModModel
{

    //// INTERNAL STATE \\\\

    private val idSequence = AtomicInteger(0)

    private val allNodes = HashMap<Int,Node>(1024)

    private var projectRoot: ProjectRoot? = null




    //// INTERNAL PROTOCOL PROCEDURES \\\


    override fun takeNewId() : Int =  idSequence.incrementAndGet()


    override fun registerNode(node: Node)
    {
        allNodes.put(node.id, node)
    }

    override fun unregisterNode(node: Node)
    {
        allNodes.remove(node.id)
    }

    override fun asIntModel(): IntModel = this


    //// ACCESS AND CONCURRENCY \\\\

    /**
     * Lock the model.
     * All read and writes must be inside the lock.
     **/
    private val mainLock = ReentrantReadWriteLock()

    private var writingThreadId: Long = 0;


    public override fun<T> read ( action: (AccModel)->T ) : T
    {
        return mainLock.read<T>() {
            action(this@RealDataMagusModel)
        }
    }


    public override fun<T> modify ( remark: String, action: (ModModel)->T ) : T
    {
        return mainLock.write<T>() {
            val currentThreadId = Thread.currentThread().getId();
            if (writingThreadId == currentThreadId) {
                // nested invocation, already acquired
                action(this@RealDataMagusModel)
            }
            else {
                // first invocation
                writingThreadId = currentThreadId
                try {
                    action(this@RealDataMagusModel)
                }
                finally {
                    writingThreadId = 0:Long
                }
            }
        }
    }


    public override fun modification (node: Node)
    {
        // check access first
        if (writingThreadId == 0:Long)
            throw IllegalThreadStateException("Attempted to modify node ($node) without write ticket. No write ticket acquired.")
        val currentThreadId = Thread.currentThread().getId()
        if (currentThreadId != writingThreadId)
            throw IllegalThreadStateException("Attempted to modify node ($node) without write ticket. The write ticket acqired by thread $writingThreadId but current thread is $currentThreadId.");

        // add this node to the change list
        // TODO
    }



    //// PUBLIC UTILITY FUNCTIONS \\\\

    public override val countNodes: Int
           get() = allNodes.size

    public override fun<N:Node> node(id: Int): N
    {
        val x = allNodes[id]
        when (x) {
            null -> throw NoSuchNodeException("Node ${id} not found")
            is N -> return x
            //ASK WTF
            //else -> throw NodeClassMismatchException("Requested node ${id} of class ${T.javaClass.getSimpleName()} but this node is ${x.javaClass.getSimpleName()}")
            else -> throw NodeClassMismatchException("Requested node ${id}: type mismatch")
        }
    }

    public override fun hasNode(id: Int): Boolean = allNodes.containsKey(id)

    public override fun viewAllNodes() : Collection<Node> = allNodes.values()



    public override fun createProjectRoot() : ProjectRoot
    {
        if (projectRoot == null) {
            return modify("Creating the project root") {
                       val m = this@RealDataMagusModel;
                       val r = ProjectRoot(NIP(model = m))
                       projectRoot = r
                       r
            }
        }
        else {
            throw IllegalStateException("The project root already exists.")
        }

    }

    public override fun getProjectRoot() : ProjectRoot
    {
        val r = projectRoot  // ASK how to simplify
        if (r != null)
            return r
        else
            throw IllegalStateException("The project root is not created yet")
    }





    //// CLEANUP PROCEDURES \\\\

    public override fun dropAllNodes()
    {
        // ASK how to create an empty array for the specified type of items ?
        // val nodes = allNodes.values().toArray(Array<Node>(n))
        val nodes = ImmutableList.copyOf(allNodes.values())!!
        allNodes.clear()
        for (node in nodes.iterator())
            if (!node.dropt)
                node.drop()
    }


}



