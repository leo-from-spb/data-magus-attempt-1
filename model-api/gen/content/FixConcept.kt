// DataMagus Model Fixed Area Concept
// =======================================

package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.*


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



final class FixConSubArea
(
		id: Int,
		override val domains        : FixFamily<FixConDomain>    = EmptyFamily,
		override val entities       : FixFamily<FixConEntity>    = EmptyFamily,
		override val name           : String?                    = null,
		override val abb            : String?                    = null
)
: FixElement(id), ConSubArea



final class FixConDomain
(
	id: Int,
	override val name           : String?                    = null,
	override val abb            : String?                    = null,
	override val surrogate      : Boolean                    = false
)
: FixElement(id), ConDomain



final class FixConEntity
(
	id: Int,
	override val name           : String?                    = null,
	override val abb            : String?                    = null
)
: FixElement(id), ConEntity



final class FixConAttribute
(
	id: Int,
	override val name           : String?                    = null,
	override val abb            : String?                    = null,
	override val surrogate      : Boolean                    = false
)
: FixElement(id), ConAttribute


