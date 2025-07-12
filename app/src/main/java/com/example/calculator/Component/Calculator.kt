package com.example.calculator.Component

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.CalculatorState
import com.example.calculator.action.CalculatorAction
import com.example.calculator.action.CalculatorOperation


@Composable
fun Calculator(
    state: CalculatorState,
    buttonSpacing: Dp = 8.dp,
    modifier: Modifier,
    onAction: (CalculatorAction) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Calculator",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomEnd),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing, Alignment.Bottom)
        ) {
            AutoResizingExpressionText(expression = state.displayExpression)

            // result field
            Text(
                text = state.result,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 32.sp
                ),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.End,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f),
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ) {
                    onAction(CalculatorAction.Clear)
                }
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    backgroundColor = Color.Yellow,
                    contentColor = Color.DarkGray
                ) {
                    onAction(CalculatorAction.Delete)
                }
                CalculatorButton(
                    symbol = "÷",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                }
            }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(7))
                    }
                    CalculatorButton(
                        symbol = "8",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(8))
                    }
                    CalculatorButton(
                        symbol = "9",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(9))
                    }
                    CalculatorButton(
                        symbol = "×",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(4))
                    }
                    CalculatorButton(
                        symbol = "5",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(5))
                    }
                    CalculatorButton(
                        symbol = "6",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(6))
                    }
                    CalculatorButton(
                        symbol = "-",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(1))
                    }

                    CalculatorButton(
                        symbol = "2",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(2))
                    }
                    CalculatorButton(
                        symbol = "3",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(3))
                    }
                    CalculatorButton(
                        symbol = "+",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "00",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.DoubleZero)
                    }

                    CalculatorButton(
                        symbol = "0",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Number(0))
                    }

                    CalculatorButton(
                        symbol = ".",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                    ) {
                        onAction(CalculatorAction.Decimal)
                    }
                    CalculatorButton(
                        symbol = "=",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                        backgroundColor = Color.Green,
                        contentColor = Color.Black
                    ) {
                        onAction(CalculatorAction.Calculate)
                    }
                }
            }
        }
    }


    @SuppressLint("UnusedBoxWithConstraintsScope")
    @Composable
    fun AutoResizingExpressionText(expression: String) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
        ) {
            val fontSize = when {
                expression.length <= 10 -> MaterialTheme.typography.displayLarge.fontSize
                expression.length <= 20 -> MaterialTheme.typography.headlineLarge.fontSize
                expression.length <= 30 -> MaterialTheme.typography.headlineMedium.fontSize
                expression.length <= 40 -> MaterialTheme.typography.headlineSmall.fontSize
                else -> MaterialTheme.typography.bodyLarge.fontSize
            }

            Text(
                text = if (expression.isNotBlank()) expression else "0",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 32.sp
                ),
                overflow = TextOverflow.Ellipsis,
                fontSize = fontSize,
                maxLines = 3,
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }



