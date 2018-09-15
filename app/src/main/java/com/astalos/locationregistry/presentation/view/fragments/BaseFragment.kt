package com.astalos.locationregistry.presentation.view.fragments

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astalos.locationregistry.di.components.ActivityComponent
import com.astalos.locationregistry.presentation.view.activities.BaseActivity
import javax.inject.Inject


/**
 * @author Tomasz Czura on 9/7/18.
 */
abstract class BaseFragment : Fragment() {
    abstract val layoutId : Int

    val activityComponent: ActivityComponent by lazy {
        (activity!! as BaseActivity).activityComponent
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId, container, false)
}

abstract class BaseDialogFragment : DialogFragment() {
    abstract val layoutId : Int

    val activityComponent: ActivityComponent by lazy {
        (activity!! as BaseActivity).activityComponent
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId, container, false)
}