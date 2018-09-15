package com.astalos.locationregistry.model.database

import android.content.Context
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.interactor.OneOf
import com.astalos.locationregistry.model.entity.toEntity
import com.astalos.locationregistry.model.entity.toUser
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/10/18.
 */
class DatabaseUsersRepository @Inject constructor(private val context: Context) : IUsersRepository {
    private val database by lazy { AppDatabase.getInstance(context) }

    override fun setActiveUser(userId: Int): OneOf<Failure, User> =
            OneOf.Success(database.userDao.setActiveUser(userId.toLong()).toUser())

    override fun getActiveUser(): OneOf<Failure, User?> = OneOf.Success(database.userDao.getActiveUser()?.toUser())

    override fun createUser(user: User): OneOf<Failure, User> {
        val users = database.userDao.getAll()
        if (users.isEmpty()) {
            user.isActive = true
        }
        val userId = database.userDao.createUser(user.toEntity())
        user.id = userId.toInt()
        return OneOf.Success(user)
    }

    override fun removeUser(userId: Int): OneOf<Failure, User?> {
        val user = database.userDao.getUserWithId(userId.toLong())
        database.userDao.deleteUser(userId.toLong())
        return OneOf.Success(user?.toUser())
    }

    override fun editUser(user: User): OneOf<Failure, User> {
        database.userDao.updateUser(user.toEntity())
        return OneOf.Success(user)
    }

    override fun users(): OneOf<Failure, List<User>> = OneOf.Success(database.userDao.getAll().map { it.toUser() })

}