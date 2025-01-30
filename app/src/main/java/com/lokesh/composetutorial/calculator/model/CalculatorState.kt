package com.lokesh.composetutorial.calculator.model

data class CalculatorState(
    val num1:String="",
    val num2:String="",
    val operation: CalculatorAction.CalculatorOperation? = null,
)
