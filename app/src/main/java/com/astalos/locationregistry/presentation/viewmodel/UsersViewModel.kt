package com.astalos.locationregistry.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.users.GetUsers
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/7/18.
 */
class UsersViewModel @Inject constructor(private val getUsers: GetUsers): BaseViewModel() {

    var users = MutableLiveData<List<User>>()

    fun loadUsers() = getUsers.execute(UseCase.NoParams()) { it.oneOf( ::handleError, ::handleUsersChange) }

    private fun handleUsersChange(users: List<User>) {
        this.users.value = users
    }
}