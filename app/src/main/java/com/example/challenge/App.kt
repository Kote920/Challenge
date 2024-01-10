package com.example.challenge

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: android.app.Application() {

    companion object{
        lateinit var application: android.app.Application
            private set
    }


    override fun onCreate() {
        super.onCreate()
        application = this
    }
}