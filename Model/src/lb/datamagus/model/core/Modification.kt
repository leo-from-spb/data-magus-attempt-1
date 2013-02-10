package lb.datamagus.model.core

import java.util.Date


/**
 * Model modification.
 * Really it is a collection of node deltas.
 */
public data class Modification
(
    /**
     * Name of the modification.
     **/
    name:       String,

    /**
     * When the modification has been done.
     **/
    timestamp:  Date,

    /**
     * Modification deltas, preserving the order.
     **/
    deltas:     List<Delta>
)
{

}