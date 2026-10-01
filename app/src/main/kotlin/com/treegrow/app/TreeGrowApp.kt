package com.treegrow.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TreeGrowApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
        
        Timber.d("TreeGrow Application created")
    }
}

/** Production crash reporting tree */
class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR) {
            // Send to crash reporting service (Firebase Crashlytics, Sentry, etc.)
            // CrashlyticsReportingService.logException(t ?: Exception(message))
        }
    }
}
