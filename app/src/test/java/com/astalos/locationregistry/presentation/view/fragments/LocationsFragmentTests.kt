package com.astalos.locationregistry.presentation.view.fragments

import android.support.v7.widget.RecyclerView
import com.astalos.locationregistry.R
import com.astalos.locationregistry.TestRegistryRobolectricApplication
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.presentation.adapter.LocationsListAdapter
import com.astalos.locationregistry.presentation.view.fragments.location.LocationsFragment
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import com.astalos.locationregistry.testing.ViewModelUtils
import com.astalos.locationregistry.testing.mocks.LocationsViewModelMock
import com.astalos.locationregistry.testing.mocks.UsersViewModelMock
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.jetbrains.anko.support.v4.find
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment
import java.util.*

/**
 * @author Tomasz Czura on 9/17/18.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = TestRegistryRobolectricApplication::class)
class LocationsFragmentTests {

    private var usersViewModel = mock(UsersViewModel::class.java)
    private var locationsViewModel = mock(LocationsViewModel::class.java)

    lateinit var fragment: LocationsFragment

    @Before
    fun setUp() {
        UsersViewModelMock.intializeMock(usersViewModel)
        LocationsViewModelMock.intializeMock(locationsViewModel)

        fragment = LocationsFragment()
        fragment.viewModelFactory = ViewModelUtils.createFactoryForViewModels(usersViewModel, locationsViewModel)
        startFragment(fragment)
    }


    @Test
    fun `should getActiveUser on start`() {
        Mockito.verify(usersViewModel).getActiveUser()
    }

    @Test
    fun `should load locations from active user`() {
        usersViewModel.activeUserId.value = 1
        Mockito.verify(locationsViewModel).loadLocations(1)
    }

    @Test
    fun `should display locations`() {
        val date = Date(1362919080000)//10-03-2013 13:38

        locationsViewModel.locations.value = listOf(UserLocation(1, 1.0, 2.0, date.time, 1))

        val recyclerView = fragment.find<RecyclerView>(R.id.locationsRecyclerView)
        recyclerView.measure(100, 100)
        recyclerView.layout(0,0, 100, 100)
        val adapter = recyclerView.adapter as LocationsListAdapter
        adapter.itemCount `should be` 1
        val viewHolder = recyclerView.findViewHolderForAdapterPosition(0) as LocationsListAdapter.LocationViewHolder
        viewHolder.latitude.text `should equal` "Lat: 1.0"
        viewHolder.longitude.text `should equal` "Lng: 2.0"
        viewHolder.locationDate.text `should equal` "10-03-2013 13:38"
    }
}