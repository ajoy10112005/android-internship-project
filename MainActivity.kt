package com.example.myinternshipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myinternshipapp.ui.theme.MyInternshipAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    setContent {
        UserScreen()
    }
    }
}

@Composable
fun StudentRegistration() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Student Registration")
        TextField(value = "", onValueChange = {}, label = { Text("Name") })
        TextField(value = "", onValueChange = {}, label = { Text("Email") })
        Button(onClick = {}) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    // Added explicit theme wrapper to ensure it's used in preview
    MyInternshipAppTheme {
        StudentRegistration()
    }
}
@Composable
fun UserScreen() {

    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            users = RetrofitClient.api.getUsers()
        } catch (e: Exception) {
            error = "Failed to load data"
        }
        isLoading = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> Text("Loading...")
            error.isNotEmpty() -> Text(error)
            else -> {
                users.forEach { user ->
                    Text(text = user.name)
                }
            }
        }
    }
}