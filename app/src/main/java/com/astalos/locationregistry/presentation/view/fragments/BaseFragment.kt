package com.astalos.locationregistry.presentation.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astalos.locationregistry.di.components.ApplicationComponent
import com.astalos.locationregistry.presentation.RegistryApplication

/**
 * @author Tomasz Czura on 9/7/18.
 */
abstract class BaseFragment : Fragment() {
    abstract val layoutId : Int

    val appComponent: ApplicationComponent by lazy {
        (activity?.application as RegistryApplication).appComponent
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId, container, false)
}