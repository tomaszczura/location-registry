package com.astalos.locationregistry.presentation

import android.app.Application
import com.astalos.locationregistry.di.components.ApplicationComponent
import com.astalos.locationregistry.di.components.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

/**
 * @author Tomasz Czura on 9/4/18.
 */
class RegistryApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initializeCanaryLeak()
        appComponent.inject(this)
    }

    private fun initializeCanaryLeak() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}