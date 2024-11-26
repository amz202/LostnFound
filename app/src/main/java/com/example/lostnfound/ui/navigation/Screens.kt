package com.example.lostnfound.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenA

@Serializable
data class ItemScreenB(
    val name: String,
    val category: String,
    val foundAt: String,
    val description: String,
    val place:String
)

@Serializable
object CategoryScreenC

@Serializable
data class AddItemD(val category: String)

@Serializable
data class PropertyScreenE(
    val name: String,
    val category: String,
    val description: String,
    val foundAt: String,
    val place:String
)
