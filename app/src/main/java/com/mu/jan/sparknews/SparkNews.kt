package com.mu.jan.sparknews

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SparkNews: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}