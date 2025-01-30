package com.lokesh.composetutorial.calculator.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction){

            when(action){
                CalculatorAction.Calculate -> performCalculation()
                CalculatorAction.Clear -> state = CalculatorState()
                CalculatorAction.Decimal -> enterDecimal()
                CalculatorAction.Delete -> delete()
                is CalculatorAction.Number -> enterNumber(action.num)
                is CalculatorAction.Operation -> enterOperation(action.operation)
            }
    }

    private fun delete() {
        when{
            state.num2.isNotBlank()-> state = state.copy(
                num2 = state.num2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.num1.isNotBlank() -> state = state.copy(
                num1 = state.num1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
       if(state.operation == null && !state.num1.contains(".")
           && state.num1.isNotBlank()){
           state = state.copy(num1 = state.num1 + ".")
           return
       }
        if(!state.num2.contains(".") && state.num2.isNotBlank()){
            state = state.copy(num1 = state.num1 + ".")
        }

    }

    private fun enterOperation(operation: CalculatorAction.CalculatorOperation) {
        if(state.num1.isNotBlank()){
            state =  state.copy(operation = operation)
        }
    }

    private fun enterNumber(num: Int) {
         if(state.operation == null){
             if(state.num1.length >= MAX_NUM_LENGTH&& state.num1.isNotEmpty()){
                 return
             }else{
                 state = state.copy(
                     num1 = state.num1 + num
                 )
             }
         }else{
             if(state.num2.length >= MAX_NUM_LENGTH&&state.num2.isNotEmpty()){
                 return
             }else{
                 state = state.copy(
                     num2 = state.num2 + num)
         }

         }
    }

    private fun performCalculation() {
        val number1 = state.num1.toDoubleOrNull()
        val number2 = state.num2.toDoubleOrNull()

        if(number1!=null && number2!=null){
            val result = when(state.operation){
                CalculatorAction.CalculatorOperation.Add -> number1+number2
                CalculatorAction.CalculatorOperation.Divide -> number1/number2
                CalculatorAction.CalculatorOperation.Multiply -> number1*number2
                CalculatorAction.CalculatorOperation.Subtract -> number1-number2
                null -> return
            }
            state = state.copy(
                num1 = result.toString().take(15),
                num2 = "",
                operation = null
            )
        }

    }

    companion object{
        const val MAX_NUM_LENGTH = 8

    }

}