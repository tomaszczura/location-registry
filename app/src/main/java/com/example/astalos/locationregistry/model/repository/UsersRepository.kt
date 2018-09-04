package com.example.astalos.locationregistry.model.repository

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.IUsersRepository
import com.example.astalos.locationregistry.domain.repository.OneOf

/**
 * @author Tomasz Czura on 9/4/18.
 */
class UsersRepository : IUsersRepository {
    override fun setActiveUser(userId: Int): OneOf<Failure, User> = OneOf.Success(User(0, "Test"))

    override fun createUser(user: User): OneOf<Failure, User>  = OneOf.Success(User(0, "Test"))

    override fun removeUser(userId: Int): OneOf<Failure, User> = OneOf.Success(User(0, "Test"))

    override fun editUser(user: User): OneOf<Failure, User> = OneOf.Success(User(0, "Test"))

    override fun users(): OneOf<Failure, List<User>> = OneOf.Success(emptyList())
}