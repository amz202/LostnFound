package com.example.lostnfound.data

import com.example.lostnfound.network.ApiService
import com.example.lostnfound.network.request.ItemRequest
import com.example.lostnfound.network.response.ClaimResponse
import com.example.lostnfound.network.response.ItemResponse
import okhttp3.ResponseBody

interface ItemRepository{
    suspend fun getItems():List<ItemRequest>
    suspend fun getItem(name:String?):ItemRequest
    suspend fun createItem(item:ItemRequest):ItemResponse
    suspend fun deleteItem(name:String?):ResponseBody
    suspend fun claimItem(claim: ClaimResponse):Boolean
}

class ItemRepositoryImpl(private val apiService: ApiService):ItemRepository {
    override suspend fun getItems(): List<ItemRequest> = apiService.getItems()
    override suspend fun getItem(name: String?): ItemRequest = apiService.getItem(name)
    override suspend fun createItem(item: ItemRequest): ItemResponse = apiService.createItem(item)
    override suspend fun deleteItem(name: String?): ResponseBody = apiService.deleteItem(name)
    override suspend fun claimItem(claim: ClaimResponse): Boolean = apiService.claimItem(claim)
}