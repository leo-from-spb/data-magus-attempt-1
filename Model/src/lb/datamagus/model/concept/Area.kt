package lb.datamagus.model.concept;

import lb.datamagus.model.core.*
import lb.datamagus.model.core.Node.Family


public abstract class Area (nip:NIP) : Bone (nip)
{

    //// SUBJECT AREAS \\\\

    public val subAreas: Family<SubArea> = makeFamily(javaClass<SubArea>())



    //// ENTITIES \\\\

    public val entities: Family<Entity> = makeFamily(javaClass<Entity>())




}



