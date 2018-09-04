package com.example.astalos.locationregistry.domain.interactor

import com.example.astalos.locationregistry.domain.entities.UserLocation
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.ILocationsRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class GetLocations @Inject constructor(private val repository: ILocationsRepository) : UseCase<List<UserLocation>, UserIdParams>() {
    override suspend fun run(params: UserIdParams): OneOf<Failure, List<UserLocation>> = repository.locations(params.userId)
}

class SaveLocation @Inject constructor(private val repository: ILocationsRepository) : UseCase<UserLocation, UserLocationParams>() {
    override suspend fun run(params: UserLocationParams): OneOf<Failure, UserLocation> = repository.addLocation(params.location)
}

class RemoveLocation @Inject constructor(private val repository: ILocationsRepository) : UseCase<UserLocation, LocationIdParams>() {
    override suspend fun run(params: LocationIdParams): OneOf<Failure, UserLocation> = repository.removeLocation(params.locationId)
}