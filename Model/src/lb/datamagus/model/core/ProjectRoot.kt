package lb.datamagus.model.core;

import lb.kotlin.utils.*
import lb.datamagus.model.concept.Conceptual
import com.google.common.collect.ImmutableList
import java.util.List


public class ProjectRoot (bip: BIP) : Bone (bip)
{


    //// CONCEPTUALS \\\\

    public var conceptuals : ImmutableList<Conceptual>
                           = ImmutableList.of<Conceptual>()!!
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


