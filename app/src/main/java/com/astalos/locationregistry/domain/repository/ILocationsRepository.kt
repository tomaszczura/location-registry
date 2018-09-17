package com.astalos.locationregistry.domain.repository

import com.astalos.locationregistry.domain.ILocationProvider
import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.interactor.OneOf

/**
 * @author Tomasz Czura on 9/4/18.
 */
interface ILocationsRepository {
    fun locations(userId: Int): OneOf<Failure, List<UserLocation>>
    fun addLocation(location: UserLocation): OneOf<Failure, UserLocation>

    fun getCurrentLocation(provider: ILocationProvider): OneOf<Failure, SimpleLocation>
}