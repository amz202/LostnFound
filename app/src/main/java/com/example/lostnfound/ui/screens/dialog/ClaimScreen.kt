package com.example.lostnfound.ui.screens.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.network.response.ClaimResponse
import com.example.lostnfound.ui.ItemViewModel
import com.example.lostnfound.ui.navigation.ClaimConfirmG
import com.example.lostnfound.ui.navigation.ClaimScreenF
import com.example.lostnfound.ui.navigation.HomeScreenA

@Composable
fun ClaimScreen(
    viewModel: ItemViewModel,
    modifier: Modifier = Modifier,
    category: String,
    name: String,
    navController: NavHostController
) {
    var property1 by remember { mutableStateOf("") }
    var property2 by remember { mutableStateOf("") }
    var property3 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (category) {
                "Tech" -> {
                    PropertyField(
                        property1 = property1,
                        onValueChange = { property1 = it },
                        label = "Type"
                    )
                    PropertyField(
                        property1 = property2,
                        onValueChange = { property2 = it },
                        label = "Brand"
                    )
                    PropertyField(
                        property1 = property3,
                        onValueChange = { property3 = it },
                        label = "Color"
                    )
                }

                "Items" -> {
                    PropertyField(
                        property1 = property1,
                        onValueChange = { property1 = it },
                        label = "Type"
                    )
                    PropertyField(
                        property1 = property2,
                        onValueChange = { property2 = it },
                        label = "Color"
                    )
                    PropertyField(
                        property1 = property3,
                        onValueChange = { property3 = it },
                        label = "Material"
                    )
                }

                "Notes" -> {
                    PropertyField(
                        property1 = property1,
                        onValueChange = { property1 = it },
                        label = "Subject"
                    )
                    PropertyField(
                        property1 = property2,
                        onValueChange = { property2 = it },
                        label = "Color"
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = {
                    val claimItem = ClaimResponse(
                        property1 = property1,
                        property2 = property2,
                        property3 = property3
                    )
                    viewModel.claimItem(claimItem)
                    navController.navigate(
                        ClaimConfirmG(name = name)
                    )
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Claim Item")
            }
        }
    }
}

@Composable
fun ClaimConfirm(
    viewModel: ItemViewModel,
    name: String,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val approved by viewModel.itemApproved.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (approved) {
                Text("Want to claim this item?")
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = {
                        navController.navigate(HomeScreenA)
                        viewModel.deleteItem(name)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }else{
                Text("Invalid Claim")
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = {
                        navController.navigate(HomeScreenA)
                        viewModel.getItems()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }
        }
    }
}