package com.astalos.locationregistry.presentation.view.fragments.location

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.ErrorResolver
import com.astalos.locationregistry.presentation.extensions.gone
import com.astalos.locationregistry.presentation.extensions.show
import com.astalos.locationregistry.presentation.extensions.textValue
import com.astalos.locationregistry.presentation.view.fragments.BaseDialogFragment
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel
import kotlinx.android.synthetic.main.dialog_buttons.*
import kotlinx.android.synthetic.main.fragment_add_location.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.util.*

/**
 * @author Tomasz Czura on 9/10/18.
 */
class SaveLocationDialogFragment : BaseDialogFragment() {
    override val layoutId: Int = R.layout.fragment_add_location

    private lateinit var viewModel: LocationsViewModel

    companion object {
        private const val USER_ID_ARG = "user_id_arg"

        fun getInstance(userId: Int): SaveLocationDialogFragment {
            val fragment = SaveLocationDialogFragment()
            val arguments = Bundle()
            arguments.putInt(USER_ID_ARG, userId)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLocationsViewModel()
        saveBtn.onClick { onSaveClick() }
        cancelBtn.onClick { dismiss() }
        getLocationBtn.onClick { getCurrentLocation() }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelCurrentLocationSearch()
    }

    private fun initLocationsViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[LocationsViewModel::class.java]
        viewModel.currentLocation.observe(this, Observer<SimpleLocation> { handleCurrentLocation(it) })
    }

    private fun onSaveClick() {
        try {
            if (!latitude.textValue().isBlank() && !longitude.textValue().isBlank()) {
                val lat = latitude.textValue().toDouble()
                val lng = longitude.textValue().toDouble()
                val date = Date().time
                val userId = arguments?.getInt(USER_ID_ARG) ?: 0
                val location = UserLocation(null, lat, lng, date, userId)
                viewModel.saveLocation(location, ::onLocationSaved)
            }
        } catch (e: NumberFormatException) {
            toast(R.string.incorrect_location).show()
        }
    }

    private fun onLocationSaved(location: UserLocation) {
        toast(R.string.location_saved).show()
        dismiss()
    }

    private fun getCurrentLocation() {
        showLocationLoading()
        viewModel.getCurrentLocation(::handleCurrentLocationError)
    }

    private fun handleCurrentLocation(location: SimpleLocation?) {
        showLocationBtn()
        latitude.setText(location?.latitude.toString())
        longitude.setText(location?.longitude.toString())
    }

    private fun handleCurrentLocationError(error: Failure) {
        toast(ErrorResolver.getErrorResId(error)).show()
        showLocationBtn()
    }

    private fun showLocationLoading() {
        getLocationBtn.gone()
        locationProgress.show()
    }

    private fun showLocationBtn() {
        getLocationBtn.show()
        locationProgress.gone()
    }

}