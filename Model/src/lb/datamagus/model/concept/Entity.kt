package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.datamagus.model.core.Node.Family
import lb.kollect.intf.List


public class Entity (nip:NIP) : Bone (nip)
{

    //// ATTRIBUTES \\\\

    public val attributes: Family<Attribute> = makeFamily(javaClass<Attribute>())


    override fun children(): List<Node>
    {
        return attributes
    }
}


