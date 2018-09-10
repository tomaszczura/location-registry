package com.astalos.locationregistry.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.interactor.UserIdParams
import com.astalos.locationregistry.domain.interactor.UserLocationParams
import com.astalos.locationregistry.domain.interactor.locations.GetLocations
import com.astalos.locationregistry.domain.interactor.locations.SaveLocation
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/10/18.
 */
class LocationsViewModel @Inject constructor(private val getLocations: GetLocations,
                                             private val saveLocation: SaveLocation) : BaseViewModel() {
    var locations = MutableLiveData<List<UserLocation>>()

    fun loadLocations(userId: Int) {
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
}