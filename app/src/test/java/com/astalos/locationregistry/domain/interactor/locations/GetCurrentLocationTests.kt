package com.astalos.locationregistry.domain.interactor.locations

import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.interactor.OneOf
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/12/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetCurrentLocationTests {
    private lateinit var getCurrentLocation: GetCurrentLocation

    @Mock
    private lateinit var locationsRepository: ILocationsRepository

    @Before
    fun setUp() {
        getCurrentLocation = GetCurrentLocation(locationsRepository)
    }

    @Test
    fun `should call repository getCurrentLocation`() {
        runBlocking { getCurrentLocation.run(UseCase.NoParams()) }
        verify(locationsRepository, times(1)).getCurrentLocation()
    }

    @Test
    fun `should return locations obtained from locationsRepository`() {
        val location = SimpleLocation(1.0, 1.0)
        given { locationsRepository.getCurrentLocation() }.willReturn(OneOf.Success(location))
        val returnedLocation = runBlocking { getCurrentLocation.run(UseCase.NoParams()) }
        returnedLocation shouldEqual OneOf.Success(location)
    }

}