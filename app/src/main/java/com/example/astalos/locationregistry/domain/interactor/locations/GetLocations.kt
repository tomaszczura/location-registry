package com.example.astalos.locationregistry.domain.interactor.locations

import com.example.astalos.locationregistry.domain.entities.UserLocation
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.interactor.UserIdParams
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