package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.interactor.UserParams
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.interactor.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/10/18.
 */
class CreateUser @Inject constructor(private val repository: IUsersRepository) : UseCase<User, UserParams>() {
    override suspend fun run(params: UserParams): OneOf<Failure, User> = repository.createUser(params.user)
}