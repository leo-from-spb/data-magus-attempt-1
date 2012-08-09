package lb.datamagus.model.core;

import lb.datamagus.model.concept.Conceptual
import lb.kollect.const.*
import lb.kollect.intf.List
import lb.datamagus.model.core.Node.Family

public class ProjectRoot (bip: BIP) : Bone (bip)
{


    //// CONCEPTUALS \\\\

    public val conceptuals: Family<Conceptual>
                          = Family<Conceptual>(Conceptual.javaClass)




    public fun newConceptual(name: String? = null): Conceptual
    {
        val newC = Conceptual(BIP(model = model, parent = this, name = name))
        conceptuals.add(newC)
        return newC
    }



    //// OTHER \\\\

    override fun children(): List<Node>
    {
        return conceptuals
    }



}


