package com.astalos.locationregistry.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.astalos.locationregistry.model.entity.UserEntity

/**
 * @author Tomasz Czura on 9/10/18.
 */
@Dao
abstract class UserDao {

    @Query("SELECT * FROM UserEntity")
    abstract fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    abstract fun getUserWithId(userId: Long): UserEntity?

    @Insert(onConflict = REPLACE)
    abstract fun saveUser(user: UserEntity) : Long

    @Query("DELETE FROM UserEntity WHERE id = :userId")
    abstract fun deleteUser(userId: Long)

    @Query("SELECT * FROM UserEntity WHERE isActive = 1")
    abstract fun getActiveUser(): UserEntity?

    @Query("UPDATE UserEntity SET isActive = 1 WHERE id = :userId")
    protected abstract fun saveActiveUser(userId: Long)

    @Transaction
    open fun setActiveUser(userId: Long): UserEntity {
        val activeUser = getActiveUser()
        if (activeUser != null) {
            activeUser.isActive = false
            saveUser(activeUser)
        }
        saveActiveUser(userId)
        return getUserWithId(userId)!!
    }
}