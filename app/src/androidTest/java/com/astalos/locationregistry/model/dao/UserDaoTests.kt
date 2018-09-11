package com.astalos.locationregistry.model.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.astalos.locationregistry.model.database.AppDatabase
import com.astalos.locationregistry.model.entity.UserEntity
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
class UserDaoTests {
    private lateinit var userDao: UserDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        userDao = database.userDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun should_save_user() {
        val user = UserEntity(null, "TestName", true)
        userDao.createUser(user)
        val users = userDao.getAll()
        Assert.assertEquals(users.size, 1)
        Assert.assertEquals(users[0].name, "TestName")
    }

    @Test
    fun should_save_user_edition() {
        val user = UserEntity(1, "TestName", true)
        userDao.createUser(user)
        user.name = "TestName2"
        userDao.updateUser(user)
        val savedUser = userDao.getUserWithId(1L)
        Assert.assertEquals(savedUser!!.name, "TestName2")
    }

    @Test
    fun should_set_active_user() {
        val user1 = UserEntity(1, "TestName1", false)
        userDao.createUser(user1)
        userDao.setActiveUser(1)
        val activeUser = userDao.getActiveUser()
        Assert.assertEquals(activeUser!!.id, 1L)
    }

    @Test
    fun should_change_active_user() {
        val user1 = UserEntity(1, "TestName1", true)
        val user2 = UserEntity(2, "TestName2", false)
        userDao.createUser(user1)
        userDao.createUser(user2)

        userDao.setActiveUser(user2.id!!)
        val activeUser = userDao.getActiveUser()
        Assert.assertEquals(activeUser!!.id, 2L)

        val user = userDao.getUserWithId(1)
        Assert.assertEquals(user!!.isActive, false)
    }

    @Test
    fun should_remove_user() {
        val user1 = UserEntity(1, "TestName1", true)
        userDao.createUser(user1)
        userDao.deleteUser(1)
        val user = userDao.getUserWithId(1)
        Assert.assertNull(user)
    }

}