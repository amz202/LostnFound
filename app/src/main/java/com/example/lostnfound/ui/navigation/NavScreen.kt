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
                place = args.place,
                onClick = { navController.popBackStack() })
        }
        composable<CategoryScreenC> {
            com.example.lostnfound.ui.screens.dialog.CategoryScreen(
                navController = navController
            )
        }
        composable<AddItemD> {
            val args = it.toRoute<AddItemD>()
            com.example.lostnfound.ui.screens.dialog.AddItem(
                navController = navController,
                category = args.category
            )
        }
        composable<PropertyScreenE> {
            val args = it.toRoute<PropertyScreenE>()
            com.example.lostnfound.ui.screens.dialog.PropertyScreen(
                name = args.name,
                category = args.category,
                description = args.description,
                foundAt = args.foundAt,
                place = args.place,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<ClaimScreenF> {
            val args = it.toRoute<ClaimScreenF>()
            com.example.lostnfound.ui.screens.dialog.ClaimScreen(
                viewModel = viewModel,
                category = args.category,
                navController = navController,
                name = args.name
            )
        }

        composable<ClaimConfirmG> {
            val args = it.toRoute<ClaimConfirmG>()
            com.example.lostnfound.ui.screens.dialog.ClaimConfirm(
                name = args.name,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

