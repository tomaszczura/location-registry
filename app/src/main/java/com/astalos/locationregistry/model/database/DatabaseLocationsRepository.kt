package com.astalos.locationregistry.model.database

import com.astalos.locationregistry.domain.ILocationProvider
import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.interactor.OneOf
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.model.entity.toEntity
import com.astalos.locationregistry.model.entity.toUserLocation
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/11/18.
 */
class DatabaseLocationsRepository @Inject constructor() : ILocationsRepository {

    @Inject lateinit var database: AppDatabase

    override fun locations(userId: Int): OneOf<Failure, List<UserLocation>> =
            OneOf.Success(database.userLocationDao.getAllForUser(userId.toLong()).map { it.toUserLocation() })

    override fun addLocation(location: UserLocation): OneOf<Failure, UserLocation> {
        val locationId = database.userLocationDao.saveLocation(location.toEntity())
        location.id = locationId.toInt()
        return OneOf.Success(location)
    }

    override fun getCurrentLocation(provider: ILocationProvider): OneOf<Failure, SimpleLocation> = provider.getLocation()
}