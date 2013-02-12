package lb.datamagus.model.core

import lb.datamagus.model.core.Node.Ref

class DumbTestBone(nip:NIP) : Bone(nip)
{

    public var boolProp: Boolean = false
    public var intProp: Int = 0
    public var strProp: String? = null
    public val refProp: Ref<DumbTestBone> = makeRef<DumbTestBone>()

}
