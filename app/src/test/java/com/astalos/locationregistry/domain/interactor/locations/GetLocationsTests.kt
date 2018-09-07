package com.astalos.locationregistry.domain.interactor.locations

import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.interactor.UserIdParams
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.repository.OneOf
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
 * @author Tomasz Czura on 9/6/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetLocationsTests {
    private lateinit var getLocations: GetLocations
    private val locations = listOf(UserLocation(1, 1.0, 1.0, 1L, 1))

    @Mock
    private lateinit var locationsRepository: ILocationsRepository

    @Before
    fun setUp() {
        getLocations = GetLocations(locationsRepository)
    }

    @Test
    fun `should call getLocations locations`() {
        runBlocking { getLocations.run(UserIdParams(1)) }
        verify(locationsRepository, times(1)).locations(1)
    }

    @Test
    fun `should return locations obtained from locationsRepository`() {
        given { locationsRepository.locations(1) }.willReturn(OneOf.Success(locations))
        val returnedLocations = runBlocking { getLocations.run(UserIdParams(1)) }
        returnedLocations shouldEqual OneOf.Success(locations)
    }


}