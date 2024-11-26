package com.example.lostnfound.network.request

import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class ItemRequest(
    val name:String,
    val category:String,
    val description:String,
    val foundAt: String,
    val place:String,
    val property1:String?,
    val property2:String?,
    val property3:String?,
)
