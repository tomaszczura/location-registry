package com.astalos.locationregistry.di.modules

import android.content.Context
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.model.memory.MemoryLocationsRepository
import com.astalos.locationregistry.model.memory.MemoryUsersRepository
import com.astalos.locationregistry.presentation.RegistryApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Module
class ApplicationModule(private val application: RegistryApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesLocationsRepository(): ILocationsRepository = MemoryLocationsRepository()

    @Provides
    @Singleton
    fun providesUsersRepository(): IUsersRepository = MemoryUsersRepository()
}