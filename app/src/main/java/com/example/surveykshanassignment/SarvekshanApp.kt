package com.example.surveykshanassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SarvekshanApp:Application() {
    override fun onCreate() {
        super.onCreate()
        PrefManager.initialize(this)

    }
}