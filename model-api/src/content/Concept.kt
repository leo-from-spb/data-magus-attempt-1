package org.jetbrains.datamagus.model.content


interface ConElement : AbElement
{
    val name: String?
}


interface ConArea : ConElement
{

}


interface ConModel : ConArea
{

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
