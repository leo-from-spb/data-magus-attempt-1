package lb.datamagus.model.core

import lb.datamagus.model.core.Node.Family
import lb.datamagus.model.core.Node.Ref
import lb.datamagus.model.core.Node.Refs

class DumbTestBone(nip:NIP) : Bone(nip)
{

    public val kinder: Family<DumbTestBone> = makeFamily(javaClass<DumbTestBone>())
    override fun children(): List<Node> { return kinder }

    public val refProp: Ref<DumbTestBone> = makeRef<DumbTestBone>()
    public val refsProp: Refs<DumbTestBone> = makeRefs<DumbTestBone>()

    public var boolProp: Boolean = false
    public var intProp: Int = 0
    public var strProp: String? = null

}
