package com.astalos.locationregistry.presentation.view.fragments.location

import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.presentation.view.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_locations.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * @author Tomasz Czura on 9/7/18.
 */
class LocationsFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_locations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addLocationBtn.onClick { showAddLocationDialog() }
    }

    private fun showAddLocationDialog() {
        val dialog = SaveLocationDialogFragment.getInstance()
        dialog.show(fragmentManager, SaveLocationDialogFragment::class.java.name)
    }

}