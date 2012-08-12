package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.datamagus.model.core.Node.Ref


public class Attribute(nip:NIP) : Bone(nip)
{

    var specification: String = ""

    public val domain: Ref<Domain> = makeRef<Domain>()




}
