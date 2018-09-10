package com.astalos.locationregistry.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.UserIdParams
import com.astalos.locationregistry.domain.interactor.UserParams
import com.astalos.locationregistry.domain.interactor.users.GetUsers
import com.astalos.locationregistry.domain.interactor.users.SaveUser
import com.astalos.locationregistry.domain.interactor.users.SetActiveUser
import com.astalos.locationregistry.domain.repository.Failure
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/7/18.
 */
class UsersViewModel @Inject constructor(private val getUsers: GetUsers,
                                         private val saveUser: SaveUser,
                                         private val setActiveUser: SetActiveUser): BaseViewModel() {

    var users = MutableLiveData<List<User>>()

    fun loadUsers() {
        getUsers.execute(UseCase.NoParams()) { it.oneOf( ::handleError, ::handleUsersChange) }
    }

    fun saveUser(user: User, onSaved: (User) -> Unit, onError: (Failure) -> Unit) {
        saveUser.execute(UserParams(user)) {
            it.oneOf(onError) { user -> handleUserSave(user, onSaved) }
        }
    }

    fun setActiveUser(userId: Int) {
        setActiveUser.execute(UserIdParams(userId)) { it.oneOf(::handleError, ::handleSetActiveUser) }
    }

    private fun handleUsersChange(users: List<User>) {
        this.users.value = users
    }

    private fun handleUserSave(user: User, onSaved: (User) -> Unit) {
        val currentUsers = users.value?.toMutableList() ?: mutableListOf()
        currentUsers.add(0, user)
        this.users.value = currentUsers
        onSaved(user)
    }

    private fun handleSetActiveUser(user: User) {
        val currentUsers = users.value?.toMutableList() ?: mutableListOf()
        this.users.value = currentUsers.map {
            it.isActive = it.id == user.id
            it
        }
    }
}