package com.example.calculator.action

sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    object DoubleZero : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()
    object Decimal : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Calculate : CalculatorAction()
}
