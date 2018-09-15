package com.astalos.locationregistry.domain.repository

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.entities.UserLocation

/**
 * @author Tomasz Czura on 9/4/18.
 */
interface ILocationsRepository {
    fun locations(userId: Int): OneOf<Failure, List<UserLocation>>
    fun addLocation(location: UserLocation): OneOf<Failure, UserLocation>

    fun getCurrentLocation(): OneOf<Failure, SimpleLocation>
    fun cancelGetCurrentLocation() {}
}