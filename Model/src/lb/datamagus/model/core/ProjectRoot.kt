package lb.datamagus.model.core;

import lb.kotlin.utils.*
import lb.datamagus.model.concept.Conceptual
import com.google.common.collect.ImmutableList


public class ProjectRoot (bip: BIP) : Bone (bip)
{


    //// CONCEPTUALS \\\\

    public var conceptuals : ImmutableList<Conceptual>
                           = ImmutableList.of<Conceptual>()!!
           private set


    public fun newConceptual(name: String? = null): Conceptual
    {
        val newC = Conceptual(BIP(model = model, parent = this, name = name))
        conceptuals += newC
        return newC
    }





}


