package com.kmose.unittestfundamentals

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {

    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    // Instant Task Execute Rule - 같은 스레드 내에 Architecture Component 에 연관된 background job에서 실행됨
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // create Calculations instance using mockito
        calculations = Mockito.mock(Calculations::class.java)
        Mockito
            .`when`(calculations.calculateArea(2.1))
            .thenReturn(13.8474)
        Mockito
            .`when`(calculations.calculateCircumference(5.0))
            .thenReturn(31.4)
        calcViewModel = CalcViewModel(calculations)
    }

    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        calcViewModel.calculateArea(2.1)
        val result = calcViewModel.areaValue.value
        assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {
        calcViewModel.calculateCircumference(5.0)
        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("31.4")
    }
}