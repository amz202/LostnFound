package com.example.lostnfound

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
import androidx.lifecycle.ViewModelProvider
import com.example.lostnfound.ui.ItemViewModel
import com.example.lostnfound.ui.navigation.NavScreen
import com.example.lostnfound.ui.screens.HomeScreen
import com.example.lostnfound.ui.theme.LostnFoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var viewModel: ItemViewModel
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ItemViewModel.Factory).get(ItemViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            LostnFoundTheme {
                NavScreen(uiState = viewModel.uiState, viewModel = viewModel )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LostnFoundTheme {
        Greeting("Android")
    }
}