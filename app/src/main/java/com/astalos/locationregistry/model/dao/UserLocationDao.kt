package com.astalos.locationregistry.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.astalos.locationregistry.model.entity.UserLocationEntity

/**
 * @author Tomasz Czura on 9/10/18.
 */
@Dao
interface UserLocationDao {
    @Query("SELECT * FROM UserLocationEntity where userId = :userId")
    fun getAll(userId: Long): List<UserLocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLocation(location: UserLocationEntity): Long
}