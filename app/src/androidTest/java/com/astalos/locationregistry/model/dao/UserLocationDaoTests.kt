package com.astalos.locationregistry.model.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.astalos.locationregistry.model.database.AppDatabase
import com.astalos.locationregistry.model.entity.UserEntity
import com.astalos.locationregistry.model.entity.UserLocationEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * @author Tomasz Czura on 9/11/18.
 */
@RunWith(AndroidJUnit4::class)
class UserLocationDaoTests {
    private lateinit var userLocationDao: UserLocationDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        userLocationDao = database.userLocationDao

        val user = UserEntity(1, "testUser")
        database.userDao.createUser(user)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun should_save_location() {
        val location = UserLocationEntity(null, 1.0, 2.0, 1, 1)
        userLocationDao.saveLocation(location)
        val locations = userLocationDao.getAllForUser(1)
        Assert.assertEquals(locations.size, 1)
    }
}