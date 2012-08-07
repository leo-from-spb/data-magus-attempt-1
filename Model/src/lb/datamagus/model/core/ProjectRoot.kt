package lb.datamagus.model.core;

import lb.datamagus.model.concept.Conceptual
import lb.kollect.const.*

public class ProjectRoot (bip: BIP) : Bone (bip)
{


    //// CONCEPTUALS \\\\

    public var conceptuals : ConstList<Conceptual>
                           = listOf()
           private set


    /*
    override fun children(): List<Node> {
        return conceptuals
    }
    */


    public fun newConceptual(name: String? = null): Conceptual
    {
        val newC = Conceptual(BIP(model = model, parent = this, name = name))
        conceptuals += newC
        return newC
    }





}


