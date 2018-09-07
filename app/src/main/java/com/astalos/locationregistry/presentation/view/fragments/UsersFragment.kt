package com.astalos.locationregistry.presentation.view.fragments

import android.os.Bundle
import com.astalos.locationregistry.R
/**
 * @author Tomasz Czura on 9/7/18.
 */
class UsersFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }
}