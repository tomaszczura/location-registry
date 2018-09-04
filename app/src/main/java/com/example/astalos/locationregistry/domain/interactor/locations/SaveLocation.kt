package com.example.astalos.locationregistry.domain.interactor.locations

import com.example.astalos.locationregistry.domain.entities.UserLocation
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.interactor.UserLocationParams
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.ILocationsRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class SaveLocation @Inject constructor(private val repository: ILocationsRepository) : UseCase<UserLocation, UserLocationParams>() {
    override suspend fun run(params: UserLocationParams): OneOf<Failure, UserLocation> = repository.addLocation(params.location)
}