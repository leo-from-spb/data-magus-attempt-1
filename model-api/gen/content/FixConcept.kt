// DataMagus Model Fixed Area Concept
// =======================================

package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.*
import org.jetbrains.datamagus.model.content.*


final class FixConModel
(
	id: Int,
	override val domains        : FixFamily<FixConDomain>    = EmptyFamily,
	override val entities       : FixFamily<FixConEntity>    = EmptyFamily,
	override val subAreas       : FixFamily<FixConSubArea>   = EmptyFamily,
	override val name           : String?                    = null,
	override val abb            : String?                    = null
)
: FixElement(id), ConModel
{
 	override val families: List<FixFamily<FixElement>> get() = listOf(domains, entities, subAreas)
}



final class FixConSubArea
(
	id: Int,
	override val domains        : FixFamily<FixConDomain>    = EmptyFamily,
	override val entities       : FixFamily<FixConEntity>    = EmptyFamily,
	override val name           : String?                    = null,
	override val abb            : String?                    = null
)
: FixElement(id), ConSubArea
{
 	override val families: List<FixFamily<FixElement>> get() = listOf(domains, entities)
}



final class FixConDomain
(
	id: Int,
	override val name           : String?                    = null,
	override val abb            : String?                    = null,
	override val surrogate      : Boolean                    = false
)
: FixElement(id), ConDomain
{
 	override val families: List<FixFamily<FixElement>> get() = emptyList()
}



final class FixConEntity
(
	id: Int,
	override val name           : String?                    = null,
	override val abb            : String?                    = null
)
: FixElement(id), ConEntity
{
 	override val families: List<FixFamily<FixElement>> get() = emptyList()
}



final class FixConAttribute
(
	id: Int,
	override val name           : String?                    = null,
	override val abb            : String?                    = null,
	override val surrogate      : Boolean                    = false
)
: FixElement(id), ConAttribute
{
 	override val families: List<FixFamily<FixElement>> get() = emptyList()
}


