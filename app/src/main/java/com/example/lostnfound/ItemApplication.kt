package com.example.lostnfound

import android.app.Application
import com.example.lostnfound.data.AppContainer
import com.example.lostnfound.data.DefaultAppContainer

class ItemApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}