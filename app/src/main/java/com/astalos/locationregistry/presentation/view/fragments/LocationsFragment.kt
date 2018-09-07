package com.astalos.locationregistry.presentation.view.fragments

import android.os.Bundle
import com.astalos.locationregistry.R
/**
 * @author Tomasz Czura on 9/7/18.
 */
class LocationsFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_locations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }
}