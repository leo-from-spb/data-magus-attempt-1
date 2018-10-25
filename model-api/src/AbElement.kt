package org.jetbrains.datamagus.model

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

}
