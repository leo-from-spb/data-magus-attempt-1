package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.Family
import org.jetbrains.datamagus.model.org.jetbrains.datamagus.model.ancillary.Prop


interface ConElement : AbNamedElement
{

    /**
     * Abbreviation.
     */
    @Prop
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
    @Prop
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
    @Prop
    val surrogate: Boolean
}
