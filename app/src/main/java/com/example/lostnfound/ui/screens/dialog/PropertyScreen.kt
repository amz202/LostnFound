package com.example.lostnfound.ui.screens.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.ui.ItemViewModel
import com.example.lostnfound.ui.navigation.HomeScreenA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyScreen(
    name: String,
    category: String,
    description: String,
    foundAt: String,
    place:String,
    viewModel: ItemViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    var property1 by remember { mutableStateOf("") }
    var property2 by remember { mutableStateOf("") }
    var property3 by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Add Properties",
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when(category){
                    "Tech" -> {
                        PropertyField(property1 = property1, onValueChange = {property1=it} , label = "Type")
                        PropertyField(property1 = property2, onValueChange = {property2=it} , label = "Brand")
                        PropertyField(property1 = property3, onValueChange = {property3=it} , label = "Color")
                    }
                    "Items" -> {
                        PropertyField(property1 = property1, onValueChange = {property1=it} , label = "Type")
                        PropertyField(property1 = property2, onValueChange = {property2=it} , label = "Color")
                        PropertyField(property1 = property3, onValueChange = {property3=it} , label = "Material")
                    }
                    "Notes" -> {
                        PropertyField(property1 = property1, onValueChange = {property1=it} , label = "Subject")
                        PropertyField(property1 = property2, onValueChange = {property2=it} , label = "Color")
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = {
                        val newItem = ItemRequest(
                            name = name,
                            category = category,
                            foundAt = foundAt,
                            description = description,
                            place = place,
                            property1 = property1,
                            property2 = property2,
                            property3 = property3
                        )
                        viewModel.createItem(newItem)
                        navController.navigate(HomeScreenA)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add Item")
                }
            }
        }
    }
}

@Composable
fun PropertyField(property1: String, onValueChange: (String) -> Unit, label:String) {
    Column {
        TextField(
            value = property1,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}