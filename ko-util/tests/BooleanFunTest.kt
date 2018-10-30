package org.jetbrains.datamagus.util

import org.jetbrains.datamagus.testUtils.assertions.Null
import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.jetbrains.datamagus.testUtils.assertions.mustBeSameAs
import org.junit.jupiter.api.Test

class BooleanFunTest
{

    @Test
    fun convertToNumbers() {
        true.byte mustBe `1`
        true.int  mustBe 1
        
        false.byte mustBe `0`
        false.int  mustBe 0
    }


    @Test
    fun choose_long() {
        var b = true
        val x: Long = b.choose(111L, 222L)
        x mustBe 111L

        b = false
        val y: Long = b.choose(333L, 444L)
        y mustBe 444L
    }

    @Test
    fun rem_Number() {
        val value: Number = java.lang.Long(123456789L)

        var b = true
        b % value mustBeSameAs value

        b = false
        b % value mustBe Null
    }

}