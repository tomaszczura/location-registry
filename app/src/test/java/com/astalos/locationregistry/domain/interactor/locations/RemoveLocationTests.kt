package com.astalos.locationregistry.domain.interactor.locations

import com.astalos.locationregistry.domain.interactor.LocationIdParams
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/6/18.
 */
@RunWith(MockitoJUnitRunner::class)
class RemoveLocationTests {
    private lateinit var removeLocation: RemoveLocation

    @Mock
    private lateinit var locationsRepository: ILocationsRepository

    @Before
    fun setUp() {
        removeLocation = RemoveLocation(locationsRepository)
    }

    @Test
    fun `should call locationsRepository removeLocation `() {
        runBlocking { removeLocation.run(LocationIdParams(1)) }
        verify(locationsRepository, times(1)).removeLocation(1)
    }
}