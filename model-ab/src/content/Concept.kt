package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.Family


interface ConElement : AbNamedElement
{

    val abb: String?
    
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
    /**
     * Means attributes of thids domain hold surrogate data.
     */
    val surrogate: Boolean
}


interface ConEntity : ConElement
{

}


interface ConAttribute : ConElement
{
    /**
     * Means this attribute holds surrogate data.
     */
    val surrogate: Boolean
}
