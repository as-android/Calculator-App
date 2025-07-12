package com.example.calculator


data class CalculatorState(
    val expression : String = "",
    val result : String = "",
    val displayExpression: String = "",
    val showResult: Boolean = false,
    val evaluated: Boolean = false
)