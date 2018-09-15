package com.astalos.locationregistry.domain

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.OneOf

/**
 * @author Tomasz Czura on 9/12/18.
 */
interface ILocationProvider {
    abstract fun getLocation(): OneOf<Failure, SimpleLocation>
}