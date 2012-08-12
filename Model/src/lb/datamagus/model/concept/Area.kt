package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.datamagus.model.core.Node.Family
import lb.kollect.intf.List
import lb.kollect.views.JoinView
import lb.kollect.views.join


public abstract class Area (nip:NIP) : Bone (nip)
{

    //// SUBJECT AREAS \\\\

    public val subAreas: Family<SubArea> = makeFamily(javaClass<SubArea>())


    //// DOMAINS \\\\

    public val domains: Family<Domain> = makeFamily(javaClass<Domain>())


    //// ENTITIES \\\\

    public val entities: Family<Entity> = makeFamily(javaClass<Entity>())


    //// CHILDREN \\\\


    override fun children(): List<Node>
    {
        return join(subAreas, domains, entities)
    }
}



