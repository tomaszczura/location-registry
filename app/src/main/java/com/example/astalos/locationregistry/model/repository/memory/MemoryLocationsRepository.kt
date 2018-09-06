package com.example.astalos.locationregistry.model.repository.memory

import com.example.astalos.locationregistry.domain.entities.UserLocation
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.ILocationsRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class MemoryLocationsRepository @Inject constructor(): ILocationsRepository {
    private val locations = mutableListOf<UserLocation>()

    override fun locations(userId: Int): OneOf<Failure, List<UserLocation>> = OneOf.Success(locations)

    override fun addLocation(location: UserLocation): OneOf<Failure, UserLocation> {
        val addedLocation = location.copy(id = locations.size + 1)
        locations.add(addedLocation)
        return OneOf.Success(addedLocation)
    }

    override fun removeLocation(locationId: Int): OneOf<Failure, UserLocation> {
        val index = locations.indexOfFirst { it.id == locationId }
        val location = locations[index]
        locations.removeAt(index)
        return OneOf.Success(location)
    }

}