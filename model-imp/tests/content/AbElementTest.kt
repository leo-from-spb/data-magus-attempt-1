package org.jetbrains.datamagus.model.content

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AbElementTest
{

    @Test
    fun instantiate1()
    {
        val e1: ConEntity = FixConEntity(26, "Нечто")
        assertThat(e1.id).isEqualTo(26)
        assertThat(e1.name).isEqualTo("Нечто")
    }

    @Test
    fun instantiate1d()
    {
        val e1: ConEntity = FixConEntity(26)
        assertThat(e1.name).isNull()
    }

    @Test
    fun instantiate2()
    {
        val a1: ConAttribute = FixConAttribute(74, "Какой-то")
        val e1: ConEntity = FixConEntity(26, "Нечто")
    }

}