package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.interactor.UserIdParams
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.IUsersRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

class RemoveUser @Inject constructor(private val repository: IUsersRepository) : UseCase<User, UserIdParams>() {
    override suspend fun run(params: UserIdParams): OneOf<Failure, User> = repository.removeUser(params.userId)
}