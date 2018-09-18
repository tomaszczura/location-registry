package com.astalos.locationregistry.presentation.viewmodel

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.UserIdParams
import com.astalos.locationregistry.domain.interactor.UserLocationParams
import com.astalos.locationregistry.domain.interactor.locations.GetCurrentLocation
import com.astalos.locationregistry.domain.interactor.locations.GetLocations
import com.astalos.locationregistry.domain.interactor.locations.SaveLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.model.gpsLocationProvider.GPSLocationProvider
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/10/18.
 */
open class LocationsViewModel @Inject constructor(private val getLocations: GetLocations,
                                                  private val saveLocation: SaveLocation) : BaseViewModel() {

    private var getCurrentLocation: GetCurrentLocation? = null

    var locations = MutableLiveData<List<UserLocation>>()

    var currentLocation = MutableLiveData<SimpleLocation>()

    fun getCurrentLocation(activity: Activity, onError: (Failure) -> Unit) {
        if (getCurrentLocation == null) {
            getCurrentLocation = GetCurrentLocation(GPSLocationProvider(activity))
        }
        getCurrentLocation?.execute(UseCase.NoParams()) { it.oneOf({ error -> onError(error) }, ::handleCurrentLocationChange) }
    }

    open fun loadLocations(userId: Int) {
        getLocations.execute(UserIdParams(userId)) { it.oneOf(::handleError, ::handleLocationsChange) }
    }

    fun saveLocation(location: UserLocation, onSaved: (UserLocation) -> Unit) {
        saveLocation.execute(UserLocationParams(location)) {
            it.oneOf(::handleError) { location -> handleLocationSave(location, onSaved) }
        }
    }

    private fun handleLocationSave(location: UserLocation, onSaved: (UserLocation) -> Unit) {
        val currentLocations = locations.value?.toMutableList() ?: mutableListOf()
        currentLocations.add(location)
        this.locations.value = currentLocations
        onSaved(location)
    }

    private fun handleLocationsChange(locations: List<UserLocation>) {
        this.locations.value = locations
    }

    private fun handleCurrentLocationChange(location: SimpleLocation) {
        this.currentLocation.value = location
    }

    public fun cancelGetCurrentLocation() {
        getCurrentLocation?.cancel()
    }
}