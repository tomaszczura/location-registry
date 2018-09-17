package com.astalos.locationregistry.testing.mocks

import android.arch.lifecycle.MutableLiveData
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel

/**
 * @author Tomasz Czura on 9/17/18.
 */
object UsersViewModelMock {
    fun intializeMock(usersViewModel: UsersViewModel) {
        usersViewModel.users = MutableLiveData()
        usersViewModel.error = MutableLiveData()
        usersViewModel.activeUserId = MutableLiveData()
    }
}