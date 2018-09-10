package com.astalos.locationregistry.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.UserIdParams
import com.astalos.locationregistry.domain.interactor.UserParams
import com.astalos.locationregistry.domain.interactor.users.*
import com.astalos.locationregistry.domain.repository.Failure
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/7/18.
 */
class UsersViewModel @Inject constructor(private val getUsers: GetUsers,
                                         private val createUser: CreateUser,
                                         private val editUser: EditUser,
                                         private val setActiveUser: SetActiveUser,
                                         private val removeUser: RemoveUser,
                                         private val getActiveUser: GetActiveUser) : BaseViewModel() {

    var users = MutableLiveData<List<User>>()
    var activeUserId = MutableLiveData<Int>()

    fun loadUsers() {
        getUsers.execute(UseCase.NoParams()) { it.oneOf(::handleError, ::handleUsersChange) }
    }

    fun saveUser(user: User, onSaved: (User) -> Unit, onError: (Failure) -> Unit) {
        if (user.id == null) {
            createUser.execute(UserParams(user)) {
                it.oneOf(onError) { user -> handleUserCreate(user, onSaved) }
            }
        } else {
            editUser.execute(UserParams(user)) {
                it.oneOf(onError) { user -> handleUserEdit(user, onSaved) }
            }
        }

    }

    fun removeUser(userId: Int) {
        removeUser.execute(UserIdParams(userId)) { it.oneOf(::handleError, ::handleUserRemoved) }
    }

    private fun handleUserRemoved(user: User?) {
        this.users.value = this.users.value?.filter { it.id != user?.id }
    }

    fun setActiveUser(userId: Int) {
        setActiveUser.execute(UserIdParams(userId)) { it.oneOf(::handleError, ::handleSetActiveUser) }
    }

    fun getActiveUser() {
        getActiveUser.execute(UseCase.NoParams()) { it -> it.oneOf({}, ::handleGetActiveUser) }
    }

    private fun handleGetActiveUser(user: User?) {
        this.activeUserId.value = user?.id
    }

    private fun handleUsersChange(users: List<User>) {
        this.users.value = users
    }

    private fun handleUserEdit(user: User, onSaved: (User) -> Unit) {
        this.users.value = this.users.value?.map {
            if (it.id == user.id) user else it
        }
        onSaved(user)
    }

    private fun handleUserCreate(user: User, onSaved: (User) -> Unit) {
        val currentUsers = users.value?.toMutableList() ?: mutableListOf()
        currentUsers.add(0, user)
        this.users.value = currentUsers
        onSaved(user)
    }

    private fun handleSetActiveUser(user: User) {
        this.activeUserId.value = user.id
        this.users.value = this.users.value?.map {
            it.isActive = it.id == user.id
            it
        }
    }

}