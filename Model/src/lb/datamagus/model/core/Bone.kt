package lb.datamagus.model.core;



public abstract class Bone (nip:NIP) : Node (nip)
{

    var name: String = "${this.javaClass.getSimpleName()}.${id}"
        set(newName)
        {
            if ($name.equals(newName))
                return
            modification()
            $name = newName
        }


    public override val displayName: String get() = $name

}

