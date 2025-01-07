package com.example.test_doubles

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class PaymentProcessorTest{

    @Test
    fun `test stub test doubles scenario`(){
        //Arrange
        val paymentService : PaymentService = mockk()
        val paymentProcessor = PaymentProcessor(paymentService)
        every { paymentService.processPayment(any()) } returns true

        //Act
       val result= paymentProcessor.pay(100.0)

        //Assert
        assertTrue(result)
    }
}