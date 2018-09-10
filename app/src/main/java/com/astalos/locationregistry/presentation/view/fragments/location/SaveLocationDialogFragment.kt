package com.astalos.locationregistry.presentation.view.fragments.location

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.repository.Failure
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

        fun getInstance() : SaveLocationDialogFragment {
            val fragment = SaveLocationDialogFragment()
            val arguments = Bundle()
            arguments.putInt(USER_ID_ARG, 0)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLocationsViewModel()
        saveBtn.onClick { onSaveClick() }
        cancelBtn.onClick { dismiss() }
    }

    private fun initLocationsViewModel () {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[LocationsViewModel::class.java]
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

    private fun onLocationSaved (location: UserLocation) {
        toast(R.string.location_saved).show()
        dismiss()
    }

    private fun handleError(failure: Failure) {
        toast(R.string.unknown_error).show()
    }

}