package com.example

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
  Dummy -> when just mock instance of object to pass it as dependency

11-Test doubles mean is to fake unit dependencies
12-SUT stands for System Under Test
13-Why Mockk
  .First class support for Kotlin Features
  .A pure kotlin-mocking DSL for writing clean and idiomatic kotlin code
  .Mocking support for final class and methods
  .Coroutine support by default
14-Mocking types -> Strict and Relaxed
 */