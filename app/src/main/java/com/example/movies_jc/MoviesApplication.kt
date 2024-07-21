package com.example.movies_jc

import android.app.Application
import com.example.movies_jc.data.AppContainer
import com.example.movies_jc.data.DefaultAppContainer

class MoviesApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}