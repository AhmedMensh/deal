package com.example.test_doubles

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class CalculatorTest{
    @Test
    fun `test strict nature of mockk`(){
        val dependency1  : Dependency1 = mockk()
        val dependency2  : Dependency2 = mockk()
        every { dependency1.value } returns 5
        every { dependency2.value } returns 5
        val calculator = Calculator(dependency1,dependency2)
        val result = calculator.add()
        assertEquals(10,result)

    }

    @Test
    fun `test relaxed nature of mockk`(){
        val dependency1  : Dependency1 = mockk(relaxed = true)
        val dependency2  : Dependency2 = mockk(relaxed = true)
        val calculator = Calculator(dependency1,dependency2)
        val result = calculator.add()
        assertEquals(0,result)
    }


    @Test
    fun `test argument catching nature of mockk`(){
        val mathService : MathService = mockk()
        val sut : Calculator2 = Calculator2(mathService)

        val result = sut.add(10,10)
    }
}