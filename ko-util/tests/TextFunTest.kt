package org.jetbrains.datamagus.util

import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.junit.jupiter.api.Test


class TextFunTest
{

    @Test
    fun splitByFirstCap_basic1() {
        val (first, second) = "mySuperCat".splitByFirstCap()
        first mustBe "my"
        second mustBe "SuperCat"
    }

    @Test
    fun splitByFirstCap_basic2() {
        val (first, second) = "MySuperCat".splitByFirstCap()
        first mustBe "My"
        second mustBe "SuperCat"
    }

    @Test
    fun splitByFirstCap_empty() {
        val (first, second) = "".splitByFirstCap()
        first mustBe ""
        second mustBe ""
    }

}
