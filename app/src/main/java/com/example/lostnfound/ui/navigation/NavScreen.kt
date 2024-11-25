package com.example.lostnfound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lostnfound.ui.ItemViewModel
import com.example.lostnfound.ui._uiState
import com.example.lostnfound.ui.screens.ItemScreen
import kotlinx.serialization.Serializable

@Composable
fun NavScreen(uiState: _uiState, viewModel: ItemViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreenA
    ) {
        composable<HomeScreenA> {
            com.example.lostnfound.ui.screens.HomeScreen(
                uiState = uiState,
                viewModel = viewModel,
                navController
            )
        }
        composable<ItemScreenB> {
            val args = it.toRoute<ItemScreenB>()
            ItemScreen(
                category = args.category,
                description = args.description,
                foundAt = args.foundAt,
                name = args.name,
                onClick = { navController.popBackStack() })
        }
        composable<AddItemC> {
            com.example.lostnfound.ui.screens.dialog.AddItem(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

