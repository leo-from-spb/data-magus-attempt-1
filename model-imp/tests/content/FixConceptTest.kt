package org.jetbrains.datamagus.model.content

import org.jetbrains.datamagus.model.ancillary.EmptyFamily
import org.jetbrains.datamagus.model.ancillary.FixSingletonFamily
import org.jetbrains.datamagus.model.ancillary.familyOf
import org.jetbrains.datamagus.testUtils.assertions.Empty
import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.jetbrains.datamagus.testUtils.assertions.mustNotBe
import org.junit.jupiter.api.Test

class FixConceptTest
{

    @Test
    fun emptyConModel_1()
    {
        val m = FixConModel(
                123,
                EmptyFamily,
                EmptyFamily,
                EmptyFamily,
                "EmptyModel",
                "EM"
        )
        m.subAreas mustBe Empty
        m.domains mustBe Empty
        m.entities mustBe Empty
    }

    @Test
    fun emptyConModel_2()
    {
        val m = FixConModel(
                123,
                name ="EmptyModel",
                abb = "EM"
        )
        m.subAreas mustBe Empty
        m.domains mustBe Empty
        m.entities mustBe Empty
    }

    @Test
    fun emptyConModel_3()
    {
        val m = FixConModel(
                123
        )
        m.subAreas mustBe Empty
        m.domains mustBe Empty
        m.entities mustBe Empty
    }

    @Test
    fun modelWithOneChild_1()
    {
        val m = FixConModel(
                1,
                subAreas = FixSingletonFamily(
                        FixConSubArea(2, name = "Test SubArea")
                )
        )
        m.subAreas mustNotBe Empty
        m.subAreas.first.id mustBe 2
    }

    @Test
    fun modelWithTwoChildren()
    {
        val m = FixConModel(
                1,
                subAreas = familyOf(
                    FixConSubArea(2, name = "First SubArea"),
                    FixConSubArea(3, name = "Second SubArea")
                )
        )
        m.subAreas mustNotBe Empty
        m.subAreas.first.id mustBe 2
    }

    @Test
    fun model_1()
    {
        val m = FixConModel(
                1,
                subAreas = familyOf(
                    FixConSubArea(
                        id = 2, name = "Test SubArea",
                        domains = familyOf(
                            FixConDomain(3, "Word", "W"),
                            FixConDomain(4, "Note")
                        )
                    )
                )
        )
        m.subAreas mustNotBe Empty
        m.subAreas.first.id mustBe 2
    }

}
