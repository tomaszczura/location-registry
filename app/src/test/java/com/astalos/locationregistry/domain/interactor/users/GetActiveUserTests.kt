package com.astalos.locationregistry.domain.interactor.users

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.interactor.UseCase
import com.astalos.locationregistry.domain.repository.IUsersRepository
import com.astalos.locationregistry.domain.interactor.OneOf
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
class GetActiveUserTests {
    private lateinit var getActiveUser: GetActiveUser

    @Mock
    private lateinit var usersRepository: IUsersRepository

    @Before
    fun setUp() {
        getActiveUser = GetActiveUser(usersRepository)
    }

    @Test
    fun `should call usersRepository getActiveUser `() {
        runBlocking { getActiveUser.run(UseCase.NoParams()) }
        verify(usersRepository, times(1)).getActiveUser()
    }

    @Test
    fun `should return active user obtained from usersRepository`() {
        val user =  User(0, "Test User")
        given { usersRepository.getActiveUser() }.willReturn(OneOf.Success(user))
        val returnedUser = runBlocking { getActiveUser.run(UseCase.NoParams()) }
        returnedUser shouldEqual OneOf.Success(user)
    }
}