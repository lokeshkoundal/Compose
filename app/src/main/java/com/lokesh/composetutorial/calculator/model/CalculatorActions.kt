package com.lokesh.composetutorial.calculator.model

sealed class CalculatorAction {
    data class Number(val num : Int) : CalculatorAction()
    data object Clear : CalculatorAction()
    data object Delete: CalculatorAction()
    data object Decimal : CalculatorAction()
    data object Calculate : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()

    sealed class CalculatorOperation(val symbol: String) {
        data object Add : CalculatorOperation("+")
        data object Subtract : CalculatorOperation("-")
        data object Multiply : CalculatorOperation("x")
        data object Divide : CalculatorOperation("÷")
    }
}