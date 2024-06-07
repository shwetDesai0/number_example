package com.core

import android.app.Activity
import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
//import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationClass: Application() {

    private var currentActivity: Activity? = null


    @Override
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    private val activityLifecycleCallbacks: BaseActivityLifecycleCallbacks =
        object : BaseActivityLifecycleCallbacks() {
            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }
        }
}