package com.example.astalos.locationregistry.domain.interactor

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.OneOf
import com.example.astalos.locationregistry.model.repository.UsersRepository
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/4/18.
 */
class GetUsers @Inject constructor(private val repository: UsersRepository) : UseCase<List<User>, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): OneOf<Failure, List<User>> = repository.users()
}

class SaveUser @Inject constructor(private val repository: UsersRepository) : UseCase<User, UserParams>() {
    override suspend fun run(params: UserParams): OneOf<Failure, User> {
        return if (params.user.id == null) {
            repository.createUser(params.user)
        } else {
            repository.editUser(params.user)
        }
    }
}

class RemoveUser @Inject constructor(private val repository: UsersRepository) : UseCase<User, UserIdParams>() {
    override suspend fun run(params: UserIdParams): OneOf<Failure, User> = repository.removeUser(params.userId)
}

class SetActiveUser @Inject constructor(private val repository: UsersRepository) : UseCase<User, UserIdParams>() {
    override suspend fun run(params: UserIdParams): OneOf<Failure, User> = repository.setActiveUser(params.userId)
}