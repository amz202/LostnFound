package com.example.lostnfound.ui.screens.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.ui.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItem(
    modifier: Modifier = Modifier,
    viewModel: ItemViewModel,
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var foundAt by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val scrollstate = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Add Item",
                    modifier = Modifier.fillMaxWidth()
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }, modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Box(modifier =  Modifier.fillMaxSize().padding(16.dp)) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .verticalScroll(scrollstate)
                )
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = {
                        val newItem = ItemRequest(
                            name = name,
                            category = category,
                            foundAt = foundAt,
                            description = description
                        )
                        viewModel.createItem(newItem)
                        navController.popBackStack()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add Item")
                }
            }
        }
    }
}