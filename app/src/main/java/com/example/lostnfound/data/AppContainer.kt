package com.example.lostnfound.data

import com.example.lostnfound.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val itemRepository:ItemRepository
}

class DefaultAppContainer:AppContainer{
    val BASE_URL = "http://192.168.1.2:8001/"
    val json = Json{
        this.ignoreUnknownKeys = true
        coerceInputValues = true
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
    val retrofitService:ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
    override val itemRepository: ItemRepository by lazy {
        ItemRepositoryImpl(retrofitService)
    }
}