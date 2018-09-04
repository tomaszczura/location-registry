package com.example.astalos.locationregistry.model.repository

import com.example.astalos.locationregistry.domain.entities.UserLocation
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.ILocationsRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class LocationsRepository @Inject constructor(): ILocationsRepository {
    override fun locations(userId: Int): OneOf<Failure, List<UserLocation>> = OneOf.Success(emptyList())

    override fun addLocation(location: UserLocation): OneOf<Failure, UserLocation> = OneOf.Success(UserLocation(0, 0.0, 0.0, 0L, 0))

    override fun removeLocation(locationId: Int): OneOf<Failure, UserLocation> = OneOf.Success(UserLocation(0, 0.0, 0.0, 0L, 0))

}