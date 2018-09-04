package com.example.astalos.locationregistry.presentation

import android.app.Application
import com.example.astalos.locationregistry.di.components.ApplicationComponent
import com.example.astalos.locationregistry.di.components.DaggerApplicationComponent

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
        appComponent.inject(this)
    }
}