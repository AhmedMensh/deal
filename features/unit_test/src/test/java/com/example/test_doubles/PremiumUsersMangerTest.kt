package com.example.test_doubles

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class PremiumUsersMangerTest{

    @Test
    fun `test mocking test doubles`(){

        //Arrange
        val userService : UserService = mockk()
        val premiumUsersManger = PremiumUsersManger(userService)
        every { userService.getUsersCount() } returns 10
        //Act
        val result = premiumUsersManger.getUsersCount()
        //Assert
        assertEquals(10,result)
        verify (atLeast = 1){
            userService.getUsersCount()

        }
    }

    @Test
    fun `test spy test doubles`(){

        //Arrange
        val userService : UserService = RealUserService()
        val spyUserService = spyk(userService)

        val premiumUsersManger = PremiumUsersManger(spyUserService)
        //Act
        val result = premiumUsersManger.getUsersCount()
        //Assert
        assertEquals(20,result)
        verify (atLeast = 1){
            spyUserService.getUsersCount()

        }
    }
}