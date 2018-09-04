package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.interactor.UserIdParams
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.OneOf
import com.example.astalos.locationregistry.model.repository.UsersRepository
import javax.inject.Inject

class SetActiveUser @Inject constructor(private val repository: UsersRepository) : UseCase<User, UserIdParams>() {
    override suspend fun run(params: UserIdParams): OneOf<Failure, User> = repository.setActiveUser(params.userId)
}