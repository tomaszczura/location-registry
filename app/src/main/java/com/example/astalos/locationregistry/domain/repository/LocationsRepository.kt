package com.example.astalos.locationregistry.domain.repository

import com.example.astalos.locationregistry.domain.entities.UserLocation

/**
 * @author Tomasz Czura on 9/4/18.
 */
interface LocationsRepository {
    fun locations(userId: Int): OneOf<Failure, List<UserLocation>>
    fun addLocation(location: UserLocation): OneOf<Failure, UserLocation>
    fun removeLocation(locationId: Int): OneOf<Failure, UserLocation>
}