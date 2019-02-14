package org.jetbrains.datamagus.util

import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SelfTest
{

    private var presetValue: Int = 0
    private var intermediateValue: Int = 0

    @BeforeAll
    fun preset() {
        presetValue = 100
    }

    @Test @Order(1)
    fun checkPresetValue() {
        presetValue mustBe 100
    }

    @Test @Order(2)
    fun setIntermediateValue() {
        intermediateValue = 200
    }

    @Test @Order(3)
    fun checkIntermediateValue() {
        intermediateValue mustBe 200
    }

}
