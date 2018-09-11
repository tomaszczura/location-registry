package com.astalos.locationregistry.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.astalos.locationregistry.model.dao.UserDao
import com.astalos.locationregistry.model.dao.UserLocationDao
import com.astalos.locationregistry.model.entity.UserEntity
import com.astalos.locationregistry.model.entity.UserLocationEntity

/**
 * @author Tomasz Czura on 9/10/18.
 */
@Database(entities = [ UserEntity::class, UserLocationEntity::class ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val userLocationDao: UserLocationDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }
}
