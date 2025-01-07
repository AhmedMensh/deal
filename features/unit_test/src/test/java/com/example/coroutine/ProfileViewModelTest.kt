package com.example.coroutine

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest {

//    @Before
//    fun setUp() {
//        Dispatchers.setMain(StandardTestDispatcher())
//    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `test success scenario`() = runTest {

        val useCase: GetUserProfileUseCase = mockk()
        coEvery { useCase.getProfileDataAsync() } coAnswers { TestUtils.profileDummyData }
        val viewModel = ProfileViewModel(useCase)

        viewModel.getUserProfile()
        advanceUntilIdle()
        assertEquals(ProfileUIState.Success(TestUtils.profileDummyData), viewModel.uiState.value)
    }




    @Test
    fun `test failure scenario`() = runTest {

        val useCase: GetUserProfileUseCase = mockk()
        coEvery { useCase.getProfileDataAsync() } throws IllegalStateException("error")
        val viewModel = ProfileViewModel(useCase)

        viewModel.getUserProfile()
        advanceUntilIdle()
        assertEquals(ProfileUIState.Error("error"), viewModel.uiState.value)
    }

//    @After
//    fun close(){
//        Dispatchers.resetMain()
//    }
}

class MainDispatcherRule(val testDispatcher : TestDispatcher = StandardTestDispatcher()) : TestWatcher() {


    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}