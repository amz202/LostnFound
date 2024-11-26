package com.example.lostnfound.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenA

@Serializable
data class ItemScreenB(val name: String, val category: String, val foundAt: String, val description: String)

@Serializable
object CategoryScreenC

@Serializable
data class AddItemD(val category: String)
