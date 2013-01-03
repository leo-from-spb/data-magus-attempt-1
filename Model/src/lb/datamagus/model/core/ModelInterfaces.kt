package lb.datamagus.model.core


/**
 * Work model provides accessors that allow to read and write.
 **/
public trait WorkModel
{
    public fun<T> read ( action: (AccModel)->T ) : T;

    public fun<T> modify ( remark: String, action: (ModModel)->T ) : T;
}

/**
 * Internal model is designed to intercat with model nodes.
 **/
internal trait IntModel
{
    fun modification (node: Node);

    fun takeNewId() : Int;

    fun registerNode(node: Node);

    fun unregisterNode(node: Node);
}


/**
 * Accessible model allows to read model graph.
 **/
public trait AccModel
{
    public fun getProjectRoot() : ProjectRoot;

    public fun<N:Node> node(id: Int): N;

    public fun hasNode(id: Int): Boolean;

    public val countNodes: Int;

    public fun viewAllNodes() : Collection<Node>;

}


/**
 * Modifyable model allows to modify model graph.
 **/
public trait ModModel : AccModel
{

    internal fun asIntModel(): IntModel;

    public fun createProjectRoot() : ProjectRoot;

    public fun dropAllNodes();

}



