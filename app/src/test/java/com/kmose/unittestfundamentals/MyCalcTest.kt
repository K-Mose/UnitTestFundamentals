package com.kmose.unittestfundamentals

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class MyCalcTest {
    private lateinit var myCalc: MyCalc

    @Before
    fun setUp() {
        myCalc = MyCalc()
    }

    @Test
    fun calculateCircumference_radiusGiven_returnsCorrectResult() {
        val result = myCalc.calculateCircumference(2.1)
        Truth.assertThat(result).isEqualTo(13.188)
    }

    @Test
    fun calculateCircumference_zeroRadius_returnsCorrectResult() {
        val result = myCalc.calculateCircumference(0.0)
        Truth.assertThat(result).isEqualTo(0)
    }

    @Test
    fun calculateArea_radiusGiven_returnCorrectResult() {
        val result = myCalc.calculateArea(3.3)
        Truth.assertThat(result).isEqualTo(34.1946)
    }

    @Test
    fun  calculateArea_zeroRadius_returnsCorrectResult() {
        val result = myCalc.calculateArea(0.0)
        Truth.assertThat(result).isEqualTo(0)
    }
}

