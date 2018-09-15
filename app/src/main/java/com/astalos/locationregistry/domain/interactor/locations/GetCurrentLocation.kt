package com.astalos.locationregistry.domain.interactor.locations

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.interactor.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/12/18.
 */
class GetCurrentLocation @Inject constructor(val repository: ILocationsRepository) : UseCase<SimpleLocation, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): OneOf<Failure, SimpleLocation> {
        return repository.getCurrentLocation()
    }

    override fun cancel() {
        super.cancel()
        repository.cancelGetCurrentLocation()
    }
}