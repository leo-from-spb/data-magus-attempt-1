package lb.datamagus.model.concept;

import lb.datamagus.model.core.*


public class Conceptual (nip:NIP) : Area (nip)
{
    class object {}

    public val projectRoot: ProjectRoot? = nip.parent as ProjectRoot



}




