package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.calculator.Component.Calculator
import com.example.calculator.ui.theme.CalculatorTheme


class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalculatorTheme {
                val context = LocalContext.current
                val state = viewModel.state
                var showDialog by remember { mutableStateOf(false) }

                // Check for first launch
                LaunchedEffect(Unit) {
                    if (FirstLaunchPrefs.isFirstLaunch(context)) {
                        showDialog = true
                        FirstLaunchPrefs.setFirstLaunchDone(context)
                    }
                }

                Scaffold(
                    contentWindowInsets = WindowInsets.systemBars
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(innerPadding)
                    ) {
                        Calculator(
                            state = state,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .background(MaterialTheme.colorScheme.background),
                            onAction = viewModel::onAction
                        )
                    }

                    // Show dialog on first launch
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = {
                                Text("⚠️ Attention", style = MaterialTheme.typography.titleLarge)
                            },
                            text = {
                                Text(
                                    "This calculator is for learning purposes only and may make mistakes.\n\nPlease verify important results.",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            confirmButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Got it")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
