package com.example.astalos.locationregistry.di.modules

import android.content.Context
import com.example.astalos.locationregistry.model.repository.LocationsRepository
import com.example.astalos.locationregistry.model.repository.UsersRepository
import com.example.astalos.locationregistry.presentation.RegistryApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Module
abstract class ApplicationModule(private val application: RegistryApplication) {

    @Binds
    @Singleton
    fun provideApplicationContext(): Context = application

    @Binds
    @Singleton
    abstract fun providesLocationsRespository(repository: LocationsRepository)

    @Binds
    @Singleton
    abstract fun providesUsersRespository(repository: UsersRepository)
}