package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.UserIdParams
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

class RemoveUser @Inject constructor(private val repository: IUsersRepository) : UseCase<User?, UserIdParams>() {
    override suspend fun run(params: UserIdParams): OneOf<Failure, User?> = repository.removeUser(params.userId)
}