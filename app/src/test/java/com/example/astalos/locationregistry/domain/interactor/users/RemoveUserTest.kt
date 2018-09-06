package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.interactor.UserIdParams
import com.example.astalos.locationregistry.domain.repository.IUsersRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/6/18.
 */
@RunWith(MockitoJUnitRunner::class)
class RemoveUserTest {
    private lateinit var removeUser: RemoveUser

    @Mock
    private lateinit var usersRepository: IUsersRepository

    @Before
    fun setUp() {
        removeUser = RemoveUser(usersRepository)
    }

    @Test
    fun `should call usersRepository removeUser `() {
        runBlocking { removeUser.run(UserIdParams(1)) }
        verify(usersRepository, times(1)).removeUser(1)
    }
}