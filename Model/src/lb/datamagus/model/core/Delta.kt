package lb.datamagus.model.core



/**
 * One node delta (a change between old and new state of the node).
 *
 * A value object.
 * Doesn't contain references to the node or it parts.
 */
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
         * Node's display name. To show it in the right column.
         * TBD in case of the name is changed - shoud it be the old or the new name?
         **/
        val nodeDisplayName: String,

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