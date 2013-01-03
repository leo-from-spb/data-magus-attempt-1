package lb.datamagus.model.core;



public abstract class Bone (nip:NIP) : Node (nip)
{

    var name: String = "Bone ${id}"
        set(newName)
        {
            if ($name.equals(newName))
                return
            model.modification(this)
            $name = newName
        }




}

