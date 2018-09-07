package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.repository.OneOf
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/6/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetUsersTests {
    private lateinit var getUsers: GetUsers
    private var users = listOf(User(1, "Test user"))

    @Mock
    private lateinit var usersRepository: IUsersRepository

    @Before
    fun setUp() {
        getUsers = GetUsers(usersRepository)
    }

    @Test
    fun `should call usersRepository users `() {
        runBlocking { getUsers.run(UseCase.NoParams()) }
        verify(usersRepository, times(1)).users()
    }

    @Test
    fun `should return users obtained from usersRepository`() {
        given { usersRepository.users() }.willReturn(OneOf.Success(users))
        val returnedUsers = runBlocking { getUsers.run(UseCase.NoParams()) }
        returnedUsers shouldEqual OneOf.Success(users)
    }
}