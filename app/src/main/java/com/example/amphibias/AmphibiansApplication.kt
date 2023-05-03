package com.example.amphibias

import android.app.Application
import com.example.amphibias.repository.classes.DefaultAppContainer
import com.example.amphibias.repository.interfaces.AppContainer

class AmphibiansApplication:Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}