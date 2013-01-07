package lb.datamagus.model.core



public data class Delta
(



        /**
         * Node identifier this delta is applicable to.
         **/
        val id: Int,

        /**
         * Kind of delta.
         **/
        val kind: Delta.Kind,

        val nodeDesc: NodeDescriptor,

        val props: List<Delta.Prop<Any?>>

)
{

    public enum class Kind
    {
        Create; Change; Delete;
    }


    public data class Prop<out P>
    (
        val property: PropertyDescriptor,
        val old: P,
        val neo: P
    )
    {}


}