package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.Family
import org.jetbrains.datamagus.model.org.jetbrains.datamagus.model.ancillary.Prop

/**
 * Abstract model element.
 * Every model element must implement this interface.
 */
interface AbElement
{

    /**
     * Surrogate identifier, unique in the cope of a model.
     * All different model elements have different identifiers,
     * but all versions of one element have same identifiers.
     */
    val id: Int

    /**
     * All families, including empty ones, in the stable pre-defined order.
     */
    val families: List<Family<AbElement>>

}


/**
 * Abstract element with name.
 */
interface AbNamedElement : AbElement
{

    /**
     * The name of the element.
     */
    @Prop
    val name: String?

}

