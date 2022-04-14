package com.kmose.unittestfundamentals

class MyCalc : Calculations{
    private val pi = 3.14

    override fun calculateCircumference(radius: Double): Double =  2 * pi * radius

    override fun calculateArea(radius: Double): Double = pi * radius * radius
}