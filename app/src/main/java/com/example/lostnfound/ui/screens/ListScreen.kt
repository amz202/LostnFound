package com.example.lostnfound.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.ui.ItemViewModel

@Composable
fun ListScreen(
    viewModel: ItemViewModel,
    modifier: Modifier = Modifier,
    itemList: List<ItemRequest>
){
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(itemList) { itemRequest ->
            ListItem(
                itemRequest = itemRequest,
                onClick_Del = { viewModel.deleteItem(itemRequest.name) }
            )
        }
    }
}

@Composable
fun ListItem(
    itemRequest: ItemRequest,
    modifier: Modifier = Modifier,
//    onClick1: () -> Unit = {},
    onClick_Del: () -> Unit = {}
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
//            .clickable { onClick1() }
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