package com.example.lostnfound.network

import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.network.response.ClaimResponse
import com.example.lostnfound.network.response.ItemResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("get-items")
    suspend fun getItems():List<ItemRequest>

    @GET("get-item")
    suspend fun getItem(@Query("name") name:String?):ItemRequest //thing inside query will append to url

    @POST("create-item")
    suspend fun createItem(@Body item: ItemRequest): ItemResponse

    @DELETE("delete-item")
    suspend fun deleteItem(@Query("name") name: String?): ResponseBody

    @POST("claim-item")
    suspend fun claimItem(@Body claim: ClaimResponse): Boolean
}