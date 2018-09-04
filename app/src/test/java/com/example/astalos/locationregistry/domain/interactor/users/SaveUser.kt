package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.interactor.UserParams
import com.example.astalos.locationregistry.model.repository.UsersRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/4/18.
 */
@RunWith(MockitoJUnitRunner::class)
class SaveUserTest {

    private lateinit var saveUser: SaveUser

    @Mock private lateinit var usersRepository: UsersRepository

    @Before fun setUp() {
        saveUser = SaveUser(usersRepository)
    }

    @Test fun `should run usersRepository createUser if passed user has no id`() {
        val user = User(null,"Test User")
        runBlocking { saveUser.run(UserParams(user)) }
        verify(usersRepository, times(1)).createUser(user)
    }

    @Test fun `should run usersRepository editUser if passed user has id`() {
        val user = User(1,"Test User")
        runBlocking { saveUser.run(UserParams(user)) }
        verify(usersRepository, times(1)).editUser(user)
    }
}