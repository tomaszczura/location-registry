package com.astalos.locationregistry.domain.repository

import com.astalos.locationregistry.domain.entities.User

/**
 * @author Tomasz Czura on 9/4/18.
 */
interface IUsersRepository {
    fun setActiveUser(userId: Int): OneOf<Failure, User>
    fun getActiveUser(): OneOf<Failure, User?>
    fun createUser(user: User): OneOf<Failure, User>
    fun removeUser(userId: Int): OneOf<Failure, User?>
    fun editUser(user: User): OneOf<Failure, User>
    fun users(): OneOf<Failure, List<User>>
}