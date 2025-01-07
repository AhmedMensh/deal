package com.example.test_doubles

import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserMangerTest {

    @Test
    fun `test dummy test doubles`(){
        //Arrange
        val logger : Logger = mockk()
        val userManger = UserManger(logger)

        //Act
        userManger.addUser(User("Ahmed"))

        //Assert
        assertEquals(1, userManger.users.size)

    }
}