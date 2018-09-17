package com.astalos.locationregistry.presentation

import android.app.Activity
import android.app.Application
import com.astalos.locationregistry.di.AppInjector
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class RegistryApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
//        initializeCanaryLeak()
    }

    private fun initializeCanaryLeak() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}