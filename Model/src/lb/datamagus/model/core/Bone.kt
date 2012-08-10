package lb.datamagus.model.core;



public abstract class Bone (nip:NIP) : Node (nip)
{

    var name: String = "Bone ${id}"
        set(newName)
        {
            $name = newName
        }




}

