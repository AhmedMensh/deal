package com.example.leads

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.lang.IllegalArgumentException

class LeadsControllerTest {

    private var controller: LeadsController ? = null

    @Before
    fun setup(){
        controller = LeadsController()
    }
    @Test
    fun `Given valid lead When call addLead() Then leads list increases by one`() {
        //Arrange
        val lead = Lead("Ahmed", "Mensh", "01284596559")
        //Act
        controller?.addLead(lead)
        //Assert
        assertEquals(1, controller?.leads?.size)
    }

    @Test
    fun `Given lead whit 2 char in first name when call addLead Then leads list should be empty`(){
        //Arrange
        val lead = Lead("ah","Mensh","01284596559")
        //Act
        controller?.addLead(lead)
        //Assert
        assertTrue(controller?.leads?.isEmpty() == true)
    }


    @Test
    fun `Given lead whit 2 char in last name when call addLead Then leads list should be empty`(){
        //Arrange
        val lead = Lead("Ahmed","me","01284596559")
        //Act
        controller?.addLead(lead)
        //Assert
        assertTrue(controller?.leads?.isEmpty() == true)
    }

    @Ignore("Ignore this test case for now")
    @Test
    fun `Given lead whit not valid phone number when call addLead Then leads list should be empty`(){
        //Arrange
        val lead = Lead("Ahmed","Mensh","1215125")
        //Act
        controller?.addLead(lead)
        //Assert
        assertTrue(controller?.leads?.isEmpty() == true)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given lead whit  phone number with 11 char when call addLead Then leads list should be empty`(){
        //Arrange
        val lead = Lead("Ahmed","Mensh","12cvnjnfjnv")
        //Act
        controller?.addLead(lead)
        //Assert
        assertTrue(controller?.leads?.isEmpty() == true)
    }
    @Test
    fun `Given lead whit  duplicated leads when call addLead Then leads list size should one`(){
        //Arrange
        val lead = Lead("Ahmed","Mensh","01284596559")
        //Act
        controller?.addLead(lead)
        controller?.addLead(lead)
        //Assert
        assertEquals(1, controller?.leads?.size)

    }

    @After
    fun close(){
        controller = null
    }
}