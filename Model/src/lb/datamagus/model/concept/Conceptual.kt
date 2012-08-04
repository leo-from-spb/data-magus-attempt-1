package lb.datamagus.model.concept;

import lb.datamagus.model.core.BIP
import lb.datamagus.model.core.Bone
import lb.datamagus.model.core.ProjectRoot


public class Conceptual (bip:BIP) : Area (bip)
{

    public val projectRoot: ProjectRoot? = bip.parent as ProjectRoot



}




