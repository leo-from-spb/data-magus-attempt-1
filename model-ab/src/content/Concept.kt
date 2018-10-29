package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.Family


interface ConElement : AbNamedElement
{
    
}


interface ConArea : ConElement
{

    val domains: Family<ConDomain>
    val entities: Family<ConEntity>

}


interface ConModel : ConArea
{

    val subAreas: Family<ConSubArea>

}


interface ConSubArea : ConArea
{

}


interface ConDomain : ConElement
{
    
}


interface ConEntity : ConElement
{

}


interface ConAttribute : ConElement
{
    
}
