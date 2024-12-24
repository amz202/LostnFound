package com.example.lostnfound.ui.screens.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostnfound.ui.theme.LostnFoundTheme

@Composable
fun CodeScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Rounded.CheckCircle, contentDescription ="check circle" , modifier = Modifier.size(120.dp), tint = Color(0,150,0))
            Spacer(modifier = Modifier.height(12.dp))
            Text("Your Code is", style = MaterialTheme.typography.headlineLarge,modifier = Modifier.padding(top = 8.dp), fontSize = 24.sp)
            Text(generateRandomCode(), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 12.dp), fontSize = 30.sp)
        }
    }
}

fun generateRandomCode(): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..5)
        .map { chars.random() }
        .joinToString("")
}

@Preview(showBackground = true)
@Composable
fun CodeScreenPreview() {
    LostnFoundTheme {
        CodeScreen()
    }
}

