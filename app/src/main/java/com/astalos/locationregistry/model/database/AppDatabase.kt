package com.astalos.locationregistry.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.astalos.locationregistry.model.dao.UserDao
import com.astalos.locationregistry.model.dao.UserLocationDao
import com.astalos.locationregistry.model.entity.UserEntity
import com.astalos.locationregistry.model.entity.UserLocationEntity

/**
 * @author Tomasz Czura on 9/10/18.
 */
@Database(entities = [ UserEntity::class, UserLocationEntity::class ], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val userLocationDao: UserLocationDao
}
