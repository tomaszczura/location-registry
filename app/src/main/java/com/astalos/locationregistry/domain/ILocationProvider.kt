package com.astalos.locationregistry.domain

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.interactor.OneOf
import com.astalos.locationregistry.domain.repository.Failure

/**
 * @author Tomasz Czura on 9/12/18.
 */
interface ILocationProvider {
    fun getLocation(): OneOf<Failure, SimpleLocation>
    fun cancel()
}