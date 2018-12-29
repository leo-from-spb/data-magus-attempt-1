package org.jetbrains.datamagus.util


import org.jetbrains.datamagus.testUtils.assertions.mustBe
import org.junit.jupiter.api.Test


internal class ChangeFunTest
{

    @Test
    fun change_Byte() {
        `2` change `2` with `3` mustBe `3`
        `2` change `4` with `5` mustBe `2`
    }

    @Test
    fun change_Byte_better() {
        `2` change 2 with 3 mustBe `3`
        `2` change 4 with 5 mustBe `2`
    }

    @Test
    fun change_Short() {
        val _123_: Short = 123
        val _456_: Short = 456
        val _789_: Short = 789
        _123_ change _123_ with _456_ mustBe _456_
        _123_ change _456_ with _789_ mustBe _123_
    }

    @Test
    fun change_Short_better() {
        val _123_: Short = 123
        val _456_: Short = 456
        _123_ change 123 with 456 mustBe _456_
        _123_ change 456 with 789 mustBe _123_
    }

    @Test
    fun change_Int() {
        123 change 123 with 456 mustBe 456
        123 change 456 with 789 mustBe 123
    }

    @Test
    fun change_Long() {
        123L change 123L with 456L mustBe 456L
        123L change 456L with 789L mustBe 123L
    }

    @Test
    fun change_Long_better() {
        123L change 123 with 456 mustBe 456L
        123L change 456 with 789 mustBe 123L
    }

    @Test
    fun change_Char() {
        'A' change 'A' with 'B' mustBe 'B'
        'A' change 'X' with 'Y' mustBe 'A'
    }

    @Test
    fun change_Any() {
        "A" change "A" with "B" mustBe "B"
        "A" change "X" with "Y" mustBe "A"
    }



}
