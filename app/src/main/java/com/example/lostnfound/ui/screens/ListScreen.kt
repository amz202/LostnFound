package com.example.lostnfound.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.ui.ItemViewModel
import com.example.lostnfound.ui.navigation.AddItemD
import com.example.lostnfound.ui.navigation.CategoryScreenC
import com.example.lostnfound.ui.navigation.ItemScreenB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ItemViewModel,
    modifier: Modifier = Modifier,
    itemList: List<ItemRequest>,
    navController: NavHostController,

    ) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(CategoryScreenC) },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
            }
        },
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Lost N Found",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            })
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingvalues ->

        LazyColumn(modifier = modifier.padding(paddingvalues)) {
            items(itemList) { itemRequest ->
                ListItem(
                    itemRequest = itemRequest,
                    onClick_Del = { viewModel.deleteItem(itemRequest.name) },
                    onClick1 = {
                        navController.navigate(
                            ItemScreenB(
                                name = itemRequest.name,
                                category = itemRequest.category,
                                foundAt = itemRequest.foundAt,
                                description = itemRequest.description
                            )
                        )
                    }
                )
            }
        }


    }
}

@Composable
fun ListItem(
    itemRequest: ItemRequest,
    modifier: Modifier = Modifier,
    onClick1: () -> Unit = {},
    onClick_Del: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
            .clickable { onClick1() }
    ) {
        Row {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = itemRequest.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = itemRequest.category,
                    fontSize = 16.sp
                )
                Text(
                    text = itemRequest.description,
                    fontSize = 16.sp
                )
                Text(
                    text = itemRequest.foundAt,
                    fontSize = 16.sp
                )
            }
            IconButton(onClick = onClick_Del) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}