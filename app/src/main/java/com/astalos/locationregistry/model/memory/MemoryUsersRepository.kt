package com.astalos.locationregistry.model.memory

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.repository.OneOf

/**
 * @author Tomasz Czura on 9/4/18.
 */
class MemoryUsersRepository : IUsersRepository {

    private val users = mutableMapOf<Int, User>()
    private var activeUserId: Int = 0

    override fun setActiveUser(userId: Int): OneOf<Failure, User> {
        return if (users[userId] != null) {
            activeUserId = userId
            OneOf.Success(users[activeUserId]!!)
        } else {
            OneOf.Error(Failure.NoUserFailure())
        }
    }

    override fun createUser(user: User): OneOf<Failure, User> {
        val addedUser = user.copy(id = users.size + 1)
        users[addedUser.id!!] = addedUser
        return OneOf.Success(addedUser)
    }

    override fun removeUser(userId: Int): OneOf<Failure, User?> {
        val user = users.remove(userId)
        return OneOf.Success(user)
    }

    override fun editUser(user: User): OneOf<Failure, User> {
        users[user.id!!] = user
        return OneOf.Success(user)
    }

    override fun users(): OneOf<Failure, List<User>> = OneOf.Success(users.map { it.value })

    override fun getActiveUser(): OneOf<Failure, User?> = OneOf.Success(users[activeUserId])
}