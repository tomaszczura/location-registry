package com.example.astalos.locationregistry.domain.interactor.locations

import com.example.astalos.locationregistry.domain.entities.UserLocation
import com.example.astalos.locationregistry.domain.interactor.UserLocationParams
import com.example.astalos.locationregistry.domain.repository.ILocationsRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/4/18.
 */
@RunWith(MockitoJUnitRunner::class)
class SaveLocationTests {

    private lateinit var saveLocation: SaveLocation

    @Mock private lateinit var locationsRepository: ILocationsRepository

    @Before fun setUp() {
        saveLocation = SaveLocation(locationsRepository)
    }

    @Test fun `should run locationsRepository addLocation if passed location has no id`() {
        val location = UserLocation(1, 1.0, 1.0, 1L, 1)
        runBlocking { saveLocation.run(UserLocationParams(location)) }
        verify(locationsRepository, times(1)).addLocation(location)
    }
}