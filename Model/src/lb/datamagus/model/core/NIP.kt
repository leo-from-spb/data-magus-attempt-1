package lb.datamagus.model.core


public open class NIP
(
        public val model:  IntModel,
        public val id:     Int = 0,
        public val parent: Node? = null
)


internal fun newNIP(model: ModModel, id: Int = 0, parent: Node? = null) : NIP
{
    return NIP(model.asIntModel(), id, parent)
}

