package com.example.leads

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.lang.IllegalArgumentException
//TODO please review unit test notes
/*
1-Test Types [White Box - Black Box] based on internal implementations
2-White Box Test [Instrumation Testing - Unit Testing - UI Testing - Snapshot testing] done by developers
3-Black Box Testing[Performance Testing -Loading Testing - Security Testing, etc...] done by serval teams like QA ,Security
4-Good Test Case [Naming,Readable(Fun Name),Simple,Check Single Thing(Fun Body)]
5-Naming very descriptive
6-Mock Http Server Library used to test api integration
7-Command "./gradlew test" use to run test on ci/cd
8-TDD(Test Driven Design) has 3 states RED -> GREEN -> REFACTOR
9-Test doubles types  [Mock ,Fake ,Stub ,Dummy ,Spy]
11-Test doubles mean is to fake unit dependencies
12-SUT stands for System Under Test
13-Why Mockk
  .First class support for Kotlin Features
  .A pure kotlin-mocking DSL for writing clean and idiomatic kotlin code
  .Mocking support for final class and methods
  .Coroutine support by default
14-Mocking types -> Strict and Relaxed
 */

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