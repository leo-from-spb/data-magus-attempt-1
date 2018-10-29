package org.jetbrains.datamagus.util.org.jetbrains.datamagus.util

import org.jetbrains.datamagus.testUtils.assertions.NotEmpty
import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.jetbrains.datamagus.testUtils.assertions.mustHasSize
import org.junit.jupiter.api.Test

class ArrayFunTest
{

    @Test
    fun convertIntArrayToByteArray_basic() {
        val ia = intArrayOf(10, 20, 30, 40)
        val ba = ia.toBytes()

        ba mustBe NotEmpty
        ba mustBe byteArrayOf(10, 20, 30, 40)
        ba mustHasSize 4
    }

    @Test
    fun convertIntArrayToShortArray_basic() {
        val ia = intArrayOf(100, 200, 300, 400)
        val ba = ia.toShorts()

        ba mustBe NotEmpty
        ba mustBe shortArrayOf(100, 200, 300, 400)
        ba mustHasSize 4
    }

}