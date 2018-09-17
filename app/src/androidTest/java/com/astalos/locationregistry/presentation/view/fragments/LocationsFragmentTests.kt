package com.astalos.locationregistry.presentation.view.fragments

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.astalos.locationregistry.presentation.view.fragments.location.LocationsFragment
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import com.astalos.locationregistry.testing.SingleFragmentActivity
import com.astalos.locationregistry.testing.ViewModelUtils
import com.astalos.locationregistry.testing.mocks.LocationsViewModelMock
import com.astalos.locationregistry.testing.mocks.UsersViewModelMock
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito

/**
 * @author Tomasz Czura on 9/17/18.
 */

@RunWith(AndroidJUnit4::class)
class LocationsFragmentEspressoTests {

    private var usersViewModel = Mockito.mock(UsersViewModel::class.java)
    private var locationsViewModel = Mockito.mock(LocationsViewModel::class.java)

    lateinit var fragment: LocationsFragment

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Before
    fun setUp() {
        UsersViewModelMock.intializeMock(usersViewModel)
        LocationsViewModelMock.intializeMock(locationsViewModel)

        fragment = LocationsFragment()
        fragment.viewModelFactory = ViewModelUtils.createFactoryForViewModels(usersViewModel, locationsViewModel)
        activityRule.activity.setFragment(fragment)
    }
}