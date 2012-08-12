package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.datamagus.model.core.Node.Refs


public class Key(nip:NIP) : Bone(nip)
{


    val attrs: Refs<Attribute> = makeRefs<Attribute>()


}
