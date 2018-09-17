package com.astalos.locationregistry.domain.interactor.locations

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.interactor.GetCurrentLocationParams
import com.astalos.locationregistry.domain.interactor.OneOf
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/12/18.
 */
class GetCurrentLocation @Inject constructor(val repository: ILocationsRepository) : UseCase<SimpleLocation, GetCurrentLocationParams>() {
    override suspend fun run(params: GetCurrentLocationParams): OneOf<Failure, SimpleLocation> =
            repository.getCurrentLocation(params.locationProvider)
}