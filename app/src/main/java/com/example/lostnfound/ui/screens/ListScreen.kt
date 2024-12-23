package com.example.lostnfound.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.lostnfound.ui.navigation.ClaimScreenF
import com.example.lostnfound.ui.navigation.ItemScreenB
import java.time.Instant
import kotlin.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ItemViewModel,
    modifier: Modifier = Modifier,
    itemList: List<ItemRequest>,
    navController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(CategoryScreenC) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lost N Found",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isDarkTheme) colorScheme.secondaryContainer else colorScheme.primary
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(itemList) { itemRequest ->
                ListItem(
                    itemRequest = itemRequest,
                    onClick_Clm = {
                        navController.navigate(
                            ClaimScreenF(
                                category = itemRequest.category,
                                name = itemRequest.name
                            )
                        )
                    },
                    onClick1 = {
                        navController.navigate(
                            ItemScreenB(
                                name = itemRequest.name,
                                category = itemRequest.category,
                                foundAt = itemRequest.foundAt,
                                description = itemRequest.description,
                                place = itemRequest.place
                            )
                        )
                    },
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListItem(
    itemRequest: ItemRequest,
    modifier: Modifier = Modifier,
    onClick1: () -> Unit = {},
    onClick_Clm: () -> Unit = {},
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
            .clickable { onClick1() }
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = itemRequest.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                AnimatedVisibility(visible = expanded) {
                    Column(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
                            .animateContentSize(animationSpec = tween(300))
                    ) {
                        InfoRow(type = "Category", info = itemRequest.category)
                        InfoRow(type = "Place", info = itemRequest.place)
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = timeAgo(itemRequest.foundAt),
                            style = MaterialTheme.typography.bodyMedium

                        )
                    }
                }
            }
            IconButton(onClick = {
                if (expanded) {
                    expanded = false
                } else {
                    expanded = true
                }
            }
            ) {
                if (expanded) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Expand")
                } else {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Collapse")
                }
            }
            IconButton(onClick = onClick_Clm) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Delete")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun timeAgo(foundAt: String): String {
    val instant = Instant.parse(foundAt)
    val now = Instant.now()
    val duration = java.time.Duration.between(instant, now)

    return when {
        duration.toMinutes() < 60 -> "${duration.toMinutes()} minutes ago"
        duration.toHours() < 24 -> "${duration.toHours()} hours ago"
        duration.toDays() < 7 -> "${duration.toDays()} days ago"
        else -> "${duration.toDays() / 7} weeks ago"
    }
}

@Composable
fun InfoRow(type: String, info: String) {
    Column {
        Spacer(modifier = Modifier.padding(2.dp))

        Row {
            Text(
                text = "$type:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = info, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.padding(2.dp))
    }
}