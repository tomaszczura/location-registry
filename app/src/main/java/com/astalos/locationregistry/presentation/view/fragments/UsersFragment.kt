package com.astalos.locationregistry.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel

/**
 * @author Tomasz Czura on 9/7/18.
 */
class UsersFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_users

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UsersViewModel::class.java]
        viewModel.users.observe(this, Observer<List<User>> { showUsers(it) })
        viewModel.error.observe(this, Observer<Failure> { handleError(it) })
    }

    private fun handleError(error: Failure?) {

    }

    private fun showUsers(users: List<User>?) {

    }
}