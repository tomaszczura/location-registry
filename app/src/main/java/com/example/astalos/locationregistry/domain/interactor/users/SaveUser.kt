package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.interactor.UserParams
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.OneOf
import com.example.astalos.locationregistry.model.repository.UsersRepository
import javax.inject.Inject

class SaveUser @Inject constructor(private val repository: UsersRepository) : UseCase<User, UserParams>() {
    override suspend fun run(params: UserParams): OneOf<Failure, User> {
        return if (params.user.id == null) {
            repository.createUser(params.user)
        } else {
            repository.editUser(params.user)
        }
    }
}