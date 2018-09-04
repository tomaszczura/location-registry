package com.example.astalos.locationregistry.di.components

import com.example.astalos.locationregistry.di.modules.ApplicationModule
import com.example.astalos.locationregistry.presentation.RegistryApplication
import dagger.Component
import javax.inject.Singleton

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Singleton
@Component(modules = [ ApplicationModule::class ])
interface ApplicationComponent {
    fun inject(application: RegistryApplication)
}