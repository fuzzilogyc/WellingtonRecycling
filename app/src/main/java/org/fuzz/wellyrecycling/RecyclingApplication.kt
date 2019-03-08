package org.fuzz.wellyrecycling

import android.app.Application
import org.fuzz.wellyrecycling.di.activityModule
import org.fuzz.wellyrecycling.di.appModule
import org.koin.android.ext.android.startKoin

class RecyclingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, activityModule))
    }
}