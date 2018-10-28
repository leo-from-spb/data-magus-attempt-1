package com.jetbrains.datamagus.model.content


interface ConElement : AbNamedElement
{
    
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
