package lb.datamagus.model.core;



public abstract class Bone (bip:BIP) : Node (bip)
{

    var name: String = bip.name ?: ""
        set(newName)
        {
            $name = newName
        }




}


public class BIP
(
               model:  Model,
               id:     Int = 0,
               parent: Node? = null,
    public val name:   String? = null
)
: NIP (model, id, parent) {}
