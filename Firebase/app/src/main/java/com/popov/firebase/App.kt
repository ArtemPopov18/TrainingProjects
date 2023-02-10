package com.popov.firebase

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        // если передать !BuildConfig.DEBUG отключиь Crashlytics для дэбаг версий, true то будет работать
    }
}