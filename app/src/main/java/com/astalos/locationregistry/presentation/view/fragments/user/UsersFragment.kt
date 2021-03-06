package com.astalos.locationregistry.presentation.view.fragments.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.adapter.UserRowActions
import com.astalos.locationregistry.presentation.adapter.UsersListAdapter
import com.astalos.locationregistry.presentation.extensions.setVisible
import com.astalos.locationregistry.presentation.view.fragments.BaseFragment
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

/**
 * @author Tomasz Czura on 9/7/18.
 */
class UsersFragment : BaseFragment(), UserRowActions {

    override val layoutId = R.layout.fragment_users

    private val usersAdapter: UsersListAdapter by lazy {
        UsersListAdapter()
    }

    private lateinit var viewModel: UsersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addUserBtn.onClick { showAddUserDialog() }
        initUsersList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()

        viewModel.loadUsers()
    }

    private fun showAddUserDialog() {
        val addUserFragment = SaveUserDialogFragment.getInstance()
        addUserFragment.show(fragmentManager, SaveUserDialogFragment::class.java.name)
    }

    private fun initUsersList() {
        usersAdapter.userActions = this
        usersRecyclerView.adapter = usersAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UsersViewModel::class.java]
        viewModel.users.observe(this, Observer<List<User>> { showUsers(it ?: emptyList()) })
        viewModel.error.observe(this, Observer<Failure> { handleError(it) })
    }

    private fun handleError(error: Failure?) {
        toast(R.string.user_fetch_error).show()
    }

    private fun showUsers(users: List<User>) {
        addFirstUserText.setVisible(users.isEmpty())
        usersAdapter.users = users
    }

    override fun onSetActiveClick(userId: Int) {
        viewModel.setActiveUser(userId)
    }

    override fun onEditClick(user: User) {
        val addUserFragment = SaveUserDialogFragment.getInstance(user)
        addUserFragment.show(fragmentManager, SaveUserDialogFragment::class.java.name)
    }

    override fun onRemoveClick(userId: Int) {
        viewModel.removeUser(userId)
    }

}