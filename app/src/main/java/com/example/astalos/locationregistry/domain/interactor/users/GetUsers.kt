package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.IUsersRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class GetUsers @Inject constructor(private val repository: IUsersRepository) : UseCase<List<User>, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): OneOf<Failure, List<User>> = repository.users()
}