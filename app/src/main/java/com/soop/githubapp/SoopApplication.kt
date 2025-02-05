package com.soop.githubapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SoopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}