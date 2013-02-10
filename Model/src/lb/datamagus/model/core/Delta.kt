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

        /**
         * Name of node class (the simple name, without package path)
         **/
        val className: String,

        /**
         * Changed or new or deleted proeprties.
         * In kind of delete, all properties with their old values should present
         * in order to get an ability to restore deleted nodes).
         **/
        val props: List<Delta.Prop>

)
{

    public enum class Kind (sign: Int) // TODO Byte
    {
        Create: Kind(-1);
        Change: Kind( 0);
        Delete: Kind(+1);
    }


    public data class Prop
    (
        val propertyName: String,
        val old: String?,
        val neo: String?
    )
    {}


}