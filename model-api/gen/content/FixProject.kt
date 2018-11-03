// DataMagus Model Fixed Area Project
// =======================================

package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.*
import org.jetbrains.datamagus.model.content.*


final class FixProProject
(
	id: Int,
	override val name           : String                     = "",
	override val version        : Int                        = 0
)
: FixElement(id), ProProject


