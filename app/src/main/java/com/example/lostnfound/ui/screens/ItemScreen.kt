package com.example.lostnfound.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(name: String, category: String, foundAt: String, description: String, place: String, onClick : () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = name, style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth()
                )
            }, navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
            )
        }
    ) { paddingValues ->
        Box(modifier=Modifier.padding(24.dp)) {
            Column(modifier = Modifier.padding(paddingValues)) {
                ItemDetail(title = "Name", info = name)
                ItemDetail(title = "Category", info = category)
                ItemDetail(title = "Place", info = place)
                ItemDetail(title = "Description", info = description)
            }
        }
    }
}

@Composable
fun ItemDetail(title:String,info:String){
    Column {
        Row {
            Text(text = "$title:", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(end = 8.dp))
            Text(text = info, style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
}