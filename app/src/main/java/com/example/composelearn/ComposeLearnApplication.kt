package com.example.composelearn

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ComposeLearnApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: ComposeLearnApplication? = null
            private set
        val appContext: Context
            get() = instance!!.applicationContext
    }
}