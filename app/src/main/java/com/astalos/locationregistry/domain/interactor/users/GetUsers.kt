package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.interactor.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class GetUsers @Inject constructor(private val repository: IUsersRepository) : UseCase<List<User>, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): OneOf<Failure, List<User>> = repository.users()
}