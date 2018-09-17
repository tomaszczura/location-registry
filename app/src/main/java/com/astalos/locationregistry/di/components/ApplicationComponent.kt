package com.astalos.locationregistry.di.components

import android.app.Application
import com.astalos.locationregistry.di.modules.ApplicationModule
import com.astalos.locationregistry.di.modules.MainActivityModule
import com.astalos.locationregistry.di.modules.RepositoriesModule
import com.astalos.locationregistry.di.modules.ViewModelModule
import com.astalos.locationregistry.presentation.RegistryApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton



/**
 * @author Tomasz Czura on 9/4/18.
 */
@Singleton
@Component(modules = [ AndroidSupportInjectionModule::class, ApplicationModule::class, MainActivityModule::class, ViewModelModule::class, RepositoriesModule::class ])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

    fun inject(application: RegistryApplication)

}