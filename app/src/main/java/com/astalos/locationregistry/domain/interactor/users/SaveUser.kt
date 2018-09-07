package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.UserParams
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

class SaveUser @Inject constructor(private val repository: IUsersRepository) : UseCase<User, UserParams>() {
    override suspend fun run(params: UserParams): OneOf<Failure, User> {
        return if (params.user.id == null) {
            repository.createUser(params.user)
        } else {
            repository.editUser(params.user)
        }
    }
}