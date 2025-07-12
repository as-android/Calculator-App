package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.action.CalculatorAction
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    private val displayOperators = listOf("×", "÷", "+", "-")  // Displayed operators (String)
    private val internalOperators = listOf("+", "-", "*", "/") // Used for logic/evaluation

    private val operatorMap = mapOf("×" to "*", "÷" to "/") // Used for evaluation

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> appendToExpression(action.number.toString())
            is CalculatorAction.DoubleZero -> appendDoubleZero()
            is CalculatorAction.Operation -> appendOperator(action.operation.symbol)
            CalculatorAction.Decimal -> appendDecimal()
            CalculatorAction.Delete -> deleteLast()
            CalculatorAction.Clear -> clearAll()
            CalculatorAction.Calculate -> evaluateExpression()
        }
    }

    private fun appendToExpression(value: String, displayValue: String = value) {
        val resolvedDisplayValue = when (value) {
            "*" -> "×"
            "/" -> "÷"
            else -> displayValue
        }

        val lastChar = state.expression.lastOrNull()
        val isOperator = value in internalOperators
        value.all { it.isDigit() }

        if (state.evaluated) {
            if (isOperator) {
                val newExpr = state.expression + value
                val newDisplayExpr = state.displayExpression + resolvedDisplayValue
                state = state.copy(
                    expression = newExpr,
                    displayExpression = newDisplayExpr,
                    evaluated = false
                )
                updateLiveResult(newExpr)
            } else {
                state = CalculatorState(
                    expression = value,
                    displayExpression = resolvedDisplayValue,
                    evaluated = false
                )
            }
            return
        }

        // Prevent multiple consecutive operators
        if (lastChar != null && lastChar.toString() in internalOperators && value in internalOperators) {
            val newExpr = state.expression.dropLast(1) + value
            val newDisplayExpr = state.displayExpression.dropLast(1) + resolvedDisplayValue
            state = state.copy(expression = newExpr, displayExpression = newDisplayExpr)
            updateLiveResult(newExpr)
            return
        }

        // Don't allow starting with operator except '-'
        if (state.expression.isEmpty() && value in internalOperators && value != "-") return

        val newExpr = state.expression + value
        val newDisplayExpr = state.displayExpression + resolvedDisplayValue
        state = state.copy(expression = newExpr, displayExpression = newDisplayExpr)
        updateLiveResult(newExpr)

    }


    private fun appendOperator(symbol: String) {
        val internalSymbol = operatorMap[symbol] ?: symbol
        appendToExpression(internalSymbol, symbol)
    }


    private fun appendDecimal() {
        val expr = state.expression
        val lastNumber = expr.split('+', '-', '*', '/').lastOrNull() ?: ""
        if (lastNumber.contains(".")) return

        val decimal = if (lastNumber.isEmpty()) "0." else "."
        appendToExpression(decimal)
    }

    private fun appendDoubleZero() {
        if (state.expression.isEmpty() || state.expression.last().toString() in displayOperators) {
            appendToExpression("0")
        } else {
            appendToExpression("00")
        }
    }

    private fun deleteLast() {
        if (state.expression.isNotEmpty()) {
            state = state.copy(
                expression = state.expression.dropLast(1),
                displayExpression = state.displayExpression.dropLast(1),
                evaluated = false
            )
            updateLiveResult(state.expression)
        }
    }

    private fun clearAll() {
        state = CalculatorState()
    }


    private fun evaluateExpression() {
        val result = safeEval(state.expression)
        val displayResult = result
            .replace("*", "×")
            .replace("/", "÷")

        state = state.copy(
            expression = result,
            displayExpression = displayResult,
            result = "",
            showResult = false,
            evaluated = true
        )

    }


    private fun updateLiveResult(expr: String) {
        val lastChar = expr.lastOrNull()

        if (lastChar == null || lastChar.toString() in internalOperators || lastChar == '.') {
            state = state.copy(result = "", showResult = false)
            return
        }

        val result = safeEval(expr)
        state = state.copy(result = result, showResult = result.isNotEmpty())
    }


    private fun safeEval(expression: String): String {
        return try {
            val formattedExpr = expression
                .replace("×", "*")
                .replace("÷", "/")

            val result = ExpressionBuilder(formattedExpr).build().evaluate()
            val bigDecimal =
                BigDecimal(result).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros()
            bigDecimal.toPlainString()
        } catch (e: Exception) {
            "Error"
        }
    }
}


