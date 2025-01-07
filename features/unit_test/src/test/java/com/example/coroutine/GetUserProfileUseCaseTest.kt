package com.example.coroutine

import com.example.test_doubles.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.coroutines.ContinuationInterceptor

class GetUserProfileUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get profile data`() = runTest {

        val userRepository = FakeUserRepository()
        val sut = GetUserProfileUseCase(userRepository)

        val result = sut.getProfileDataAsync()

        assertEquals(result, TestUtils.profileDummyData)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get profile data with test time`() = runTest {

        val userRepository= FakeUserRepository2()
        val sut = GetUserProfileUseCase(
            userRepository,
            dispatcher = this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher
        )

        val result = sut.getProfileDataSync()
        assertEquals(1000, currentTime)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get profile data using mock`() = runTest {

        val userRepository: UserRepository = mockk()
        val sut = GetUserProfileUseCase(userRepository)
        coEvery { userRepository.getName() } coAnswers { "Ahmed" }
        coEvery { userRepository.getRate() } coAnswers { 2.2f }
        coEvery { userRepository.getFriends() } coAnswers { TestUtils.profileDummyData.friends }
        val result = sut.getProfileDataAsync()

        assertEquals(result, TestUtils.profileDummyData)

    }
}

object TestUtils {
    val profileDummyData = Profile(
        name = "Ahmed",
        rate = 2.2f,
        friends = listOf(Friend("1", "Ahmed"), Friend("2", "Mensh"))
    )
}