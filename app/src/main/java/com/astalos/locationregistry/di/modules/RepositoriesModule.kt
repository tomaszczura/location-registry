package com.astalos.locationregistry.di.modules

import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.model.database.DatabaseLocationsRepository
import com.astalos.locationregistry.model.database.DatabaseUsersRepository
import dagger.Module
import dagger.Provides

/**
 * @author Tomasz Czura on 9/13/18.
 */
@Module
class RepositoriesModule {

    @Provides
    fun providesLocationsRepository(repository: DatabaseLocationsRepository): ILocationsRepository = repository

    @Provides
    fun providesUsersRepository(repository: DatabaseUsersRepository): IUsersRepository = repository
//
//    @Provides
//    fun providesGPSProvider(activity: MainActivity): ILocationProvider = GPSLocationProvider(activity)
}