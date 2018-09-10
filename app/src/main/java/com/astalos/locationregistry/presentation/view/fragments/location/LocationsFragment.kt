package com.astalos.locationregistry.presentation.view.fragments.location

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.adapter.LocationsListAdapter
import com.astalos.locationregistry.presentation.view.fragments.BaseFragment
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.fragment_locations.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/7/18.
 */
class LocationsFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_locations

    @Inject
    lateinit var locationsAdapter: LocationsListAdapter

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var locationsViewModel: LocationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addLocationBtn.onClick { showAddLocationDialog() }
        initLocationsList()
        initLocationsViewModel()
        initUsersViewModel()
    }

    private fun initLocationsList() {
        locationsRecyclerView.adapter = locationsAdapter
    }

    private fun initUsersViewModel() {
        usersViewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UsersViewModel::class.java]
        usersViewModel.activeUserId.observe(this, Observer<Int> { handleActiveUserChange(it) })
    }

    private fun initLocationsViewModel() {
        locationsViewModel = ViewModelProviders.of(activity!!, viewModelFactory)[LocationsViewModel::class.java]
        locationsViewModel.locations.observe(this, Observer<List<UserLocation>> { showLocations(it ?: emptyList()) })
        locationsViewModel.error.observe(this, Observer<Failure> { handleError(it) })
    }

    private fun showLocations(locations: List<UserLocation>) {
        locationsAdapter.locations = locations
    }

    private fun handleActiveUserChange(userId: Int?) {
        userId?.let {
            locationsViewModel.loadLocations(it)
        }
    }

    private fun showAddLocationDialog() {
        val dialog = SaveLocationDialogFragment.getInstance()
        dialog.show(fragmentManager, SaveLocationDialogFragment::class.java.name)
    }

    private fun handleError(error: Failure?) {
        toast(R.string.user_fetch_error).show()
    }

}