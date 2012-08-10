package lb.datamagus.model.core;

import lb.datamagus.model.concept.Conceptual
import lb.kollect.const.*
import lb.kollect.intf.List
import lb.datamagus.model.core.Node.Family

public class ProjectRoot (nip: NIP) : Bone (nip)
{


    //// CONCEPTUALS \\\\

    public val conceptuals: Family<Conceptual> = makeFamily(javaClass<Conceptual>())





    //// OTHER \\\\

    override fun children(): List<Node>
    {
        return conceptuals
    }



}


