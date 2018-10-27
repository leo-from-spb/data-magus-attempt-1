package org.jetbrains.datamagus.model.base

import java.util.concurrent.atomic.AtomicInteger

class BaseModel
{

    internal val elementIdCounter = AtomicInteger(0)
    internal val rootVersionCounter = AtomicInteger(0)
    
}