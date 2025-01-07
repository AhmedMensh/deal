package com.example.test_doubles

import org.junit.Assert.*
import org.junit.Test

class DatabaseMangerTest{
    @Test
    fun `test fake test doubles scenario`(){
        //Arrange
        val database : Database = InMemoryDatabase()
        val databaseManger = DatabaseManger(database)

        //Act
        databaseManger.save("Ahmed")
        val data = databaseManger.get()

        //Assert
        assertEquals("Ahmed",data)

    }
}