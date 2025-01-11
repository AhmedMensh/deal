package com.example.flows

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowsPlaygroundTest {

    @Test
    fun `test flow`() = runTest{
        val flow = flowOf(1,2,3,4)
        val result = flow.toList()
        assertEquals(listOf(1,2,3,4), result)
    }

    @Test
    fun `test flow consumer`() = runTest{
        val flow = flowOf(1,2,3,4)
        val result = mutableListOf<Int>()
        flow.collect{
            result.add(it)
        }
        assertEquals(listOf(1,2,3,4), result)
    }


    @Test
    fun `test flow consumer continualsly`() = runTest{
        val flow = flow{
            for(i in 1..4){
                emit(i)
            }
        }
        val result = mutableListOf<Int>()
        flow.collect{
            result.add(it)
        }
        assertEquals(listOf(1,2,3,4), result)
    }

    @Test
    fun `test flow consumer continualsly with delay`() = runTest{
        val flow = flow{
            for(i in 1..4){
                emit(i)
                kotlinx.coroutines.delay(200)
            }
        }
        val result = mutableListOf<Int>()
        flow.onEach{
            result.add(it)
        }.launchIn(this)
        advanceUntilIdle()
        assertEquals(listOf(1,2,3,4), result)
    }
}