package com.example.network

import com.example.model.LoginResponse

interface DealRemoteDataSource  {

    fun requestLogin(userName : String,password : String) : LoginResponse
}