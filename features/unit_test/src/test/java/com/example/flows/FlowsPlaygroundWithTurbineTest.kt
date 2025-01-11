package com.example.flows

import app.cash.turbine.test
import com.example.coroutine.ProfileUIState
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowsPlaygroundWithTurbineTest {

    @Test
    fun `test flow`() = runTest {
        val flow = flowOf(1, 2, 3, 4)

        flow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            assertEquals(4, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test flow then throw an exceptions`() = runTest {
        val flow = flow {
            emit(1)
            throw IllegalStateException("error")
        }

        flow.test {
            assertEquals(1, awaitItem())
            assertEquals("error", awaitError().message)

        }
    }

    @Test
    fun `test cold flow`() = runTest {
        val flow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        flow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }

        flow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `convert cold flow to hot flow`() = runTest {
        val flow = flowOf(1,2,3).stateIn(this)

        flow.test {
            assertEquals(3, awaitItem())
        }

    }

    @Test
    fun `test mutable state flow`() = runTest {
        val flow = MutableStateFlow<ProfileUIState>(ProfileUIState.Idle)
        flow.tryEmit(ProfileUIState.Loading)

        flow.test {
            print("FIRST FLOW")
//            assertEquals(ProfileUIState.Idle, awaitItem())
            assertEquals(ProfileUIState.Loading, awaitItem())
        }



    }

    @Test
    fun `test mutable shared flow first case`() = runTest {
        val flow = MutableSharedFlow<Int>(replay = 1)
        flow.emit(1)

        flow.test {
            assertEquals(1, awaitItem())
        }

    }


    @Test
    fun `test mutable shared flow second case`() = runTest {
        val flow = MutableSharedFlow<Int>()
        val job = launch(start = CoroutineStart.LAZY) {
            flow.emit(1)

        }

        flow.test {
            job.start()
            assertEquals(1, awaitItem())
        }

    }
}