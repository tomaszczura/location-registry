package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UserParams
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/4/18.
 */
@RunWith(MockitoJUnitRunner::class)
class CreateUserTests {

    private lateinit var createUser: CreateUser

    @Mock private lateinit var usersRepository: IUsersRepository

    @Before fun setUp() {
        createUser = CreateUser(usersRepository)
    }

    @Test fun `should run usersRepository createUser`() {
        val user = User(null,"Test User")
        runBlocking { createUser.run(UserParams(user)) }
        verify(usersRepository, times(1)).createUser(user)
    }
}