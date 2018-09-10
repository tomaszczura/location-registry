package com.astalos.locationregistry.model.memory

import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.repository.OneOf
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/6/18.
 */
@RunWith(MockitoJUnitRunner::class)
class MemoryLocationsRepositoryTests {

    @Test
    fun `addLocation should add location`() {
        val repository = MemoryLocationsRepository()
        repository.addLocation(UserLocation(null, 1.0, 1.0, 1L, 1))
        val result = repository.locations(1)
        val locations = (result as OneOf.Success<List<UserLocation>>).data
        result.isSuccess shouldEqual true
        locations.size shouldEqual 1
        locations[0] shouldEqual UserLocation(1, 1.0, 1.0, 1L, 1)
    }
}
