package com.astalos.locationregistry.testing.mocks

import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel

/**
 * @author Tomasz Czura on 9/17/18.
 */
object LocationsViewModelMock {
    fun intializeMock(locationsViewModel: LocationsViewModel) {
        locationsViewModel.locations = MutableLiveData()
        locationsViewModel.error = MutableLiveData()
    }
}