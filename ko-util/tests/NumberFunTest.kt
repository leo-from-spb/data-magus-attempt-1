package org.jetbrains.datamagus.util

import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.junit.jupiter.api.Test

class NumberFunTest
{

    @Test
    fun convertBooleanToNumbers() {
        true.byte mustBe `1`
        true.int mustBe 1

        false.byte mustBe `0`
        false.int mustBe 0
    }

}