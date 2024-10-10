package com.example.sarah_malik_hello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sarah_malik_hello.ui.theme.Sarah_malik_helloTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sarah_malik_helloTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Function to get the current time and return a greeting based on the time of day
fun getTimeBasedGreeting(): String {
    val currentHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")).toInt()

    return when (currentHour) {
        in 0..5 -> "Good night"
        in 6..11 -> "Good morning"
        in 12..17 -> "Good afternoon"
        else -> "Good evening"
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    // Mutable state to track user's input and the generated greeting
    var name by remember { mutableStateOf("") }
    var greetingMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Input field for user's name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to generate the personalized greeting
        Button(onClick = {
            if (name.isNotEmpty()) {
                // Generate a greeting based on the user's name and time of day
                val timeGreeting = getTimeBasedGreeting()
                greetingMessage = "Hello $name, $timeGreeting!"
            } else {
                greetingMessage = "Please enter your name."
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Greet Me")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the generated greeting message
        if (greetingMessage.isNotEmpty()) {
            Text(text = greetingMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    Sarah_malik_helloTheme {
        GreetingScreen()
    }
}
