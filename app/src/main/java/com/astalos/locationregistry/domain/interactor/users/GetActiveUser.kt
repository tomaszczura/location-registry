package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.interactor.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/6/18.
 */
class GetActiveUser @Inject constructor(private val usersRepository: IUsersRepository): UseCase<User?, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): OneOf<Failure, User?> = usersRepository.getActiveUser()
}